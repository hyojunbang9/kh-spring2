
package com.kh.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kh.domain.Item;
import com.kh.service.ItemMapperService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@MapperScan(basePackages = "com.kh.mapper")
@RequestMapping("/item")

public class ItemController {

	@Autowired
	private ItemMapperService itemService;

	// 저장 장소 경로 설정
	@Value("${upload.path}")
	private String uploadPath;

	// 자료 업로드 리스트 화면
	@GetMapping(value = "/list")
	public String list(Model model) throws Exception {
		List<Item> itemList = this.itemService.list();

		model.addAttribute("itemList", itemList);
		return "item/list";
	}

	// 자료 입력 화면
	@GetMapping(value = "/register")
	public String registerForm(Model model) {
		model.addAttribute("item", new Item());

		return "item/register";
	}

	// 자료 입력 내용 저장(파일을 외부 저장소에 저장 후, DB 저장)
	@PostMapping(value = "/register")
	public String register(Item item, Model model) throws Exception {
		MultipartFile file = item.getPicture();

		log.info("originalName: " + file.getOriginalFilename());
		log.info("size: " + file.getSize());
		log.info("contentType: " + file.getContentType());

		// uploadFile(String originalName, byte[] fileData) 구조임
		// 업로드 된 파일을 외부 저장소에 저장 후, 저장 파일 명을 리턴하는 함수
		// 절대 중복되지 않는 파일 명!
		String createdFileName = uploadFile(file.getOriginalFilename(), file.getBytes());
		item.setPictureUrl(createdFileName);
		itemService.create(item);
		model.addAttribute("msg", "등록이 완료되었습니다.");

		return "item/success";
	}

	// 외부 저장소에 자료 업로드 파일명 생성 후 저장
	private String uploadFile(String originalName, byte[] fileData) throws Exception {
		UUID uid = UUID.randomUUID();
		// uuid
		String createdFileName = uid.toString() + "_" + originalName;
		// D:/upload/uuid..... 파일 생성
		File target = new File(uploadPath, createdFileName);
		// 원본 파일을 대상 파일에 복사 해주는 함수
		FileCopyUtils.copy(fileData, target);
		return createdFileName;
	}

	// 수정 내용 화면
	@GetMapping(value = "/modify")
	public String modifyForm(Item item, Model model) throws Exception {
		Item _item = this.itemService.read(item);

		model.addAttribute("item", _item);

		return "item/modify";
	}

	// 수정 내용 저장
	@PostMapping(value = "/modify")
	public String modify(Item item, Model model) throws Exception {
		MultipartFile file = item.getPicture();

		if (file != null && file.getSize() > 0) {
			// 기존 외부 저장소 파일 삭제
			Item oldItem = itemService.read(item);
			String oldPictureUrl = oldItem.getPictureUrl();
			// d:/upload/uuid_kkk.jpg =>이게 파일로 인식,객체가 되는 순간
			deleteFile(oldPictureUrl);

			log.info("originalName: " + file.getOriginalFilename());
			log.info("size: " + file.getSize());
			log.info("contentType: " + file.getContentType());

			String createdFileName = uploadFile(file.getOriginalFilename(), file.getBytes());
			item.setPictureUrl(createdFileName);
		}
		// aaa.jpg 파일이 DB/외부 저장소에 저장 된 상태
		// =>수정 후 bbb.jpg로 DB/외부 저장소 저장 => 근데 외부 저장소에는 aaa.jpg가 아직 저장되어 있다.
		itemService.update(item);
		model.addAttribute("msg", "수정이 완료되었습니다.");
		return "item/success";
	}

	// 삭제 화면
	@GetMapping(value = "/remove")
	public String removeForm(Item item, Model model) throws Exception {
		Item _item = itemService.read(item);
		model.addAttribute("item", _item);
		return "item/remove";
	}

	// 삭제 요청
	@PostMapping("/remove")
	public String remove(Item item, Model model) throws Exception {
		// 기존 외부 저장소 파일 삭제
		Item oldItem = itemService.read(item);
		String oldPictureUrl = oldItem.getPictureUrl();
		boolean flag = deleteFile(oldPictureUrl);
		if (flag == true) {
			itemService.delete(item);
			model.addAttribute("msg", "삭제가 완료되었습니다.");
		} else {
			model.addAttribute("msg", "삭제 오류.");
		}
		return "item/success";
	}

	// 외부 저장소에 자료 업로드 파일명 생성 후 저장
	private boolean deleteFile(String fileName) throws Exception {
		if (fileName.contains("..")) {
			throw new IllegalArgumentException("잘못 된 경로");
		}

		// d:/upload/uuid_kkk.jpg =>이게 파일로 인식,객체가 되는 순간
		File file = new File(uploadPath, fileName);

		return (file.exists()) ? (file.delete()) : (false);
	}

	@ResponseBody
	@RequestMapping("/display")
	public ResponseEntity<byte[]> displayFile(Item item) throws Exception {
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;

		Item _item = itemService.getPicture(item);
		String fileName = _item.getPictureUrl();
		log.info("FILE NAME: " + fileName);
		try {
			// 썰어!!
			// uuid_kkk.jpg를 만나면 "." 뒤의 jpg만 사용한다.
			String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
			MediaType mType = getMediaType(formatName);
			HttpHeaders headers = new HttpHeaders();
			in = new FileInputStream(uploadPath + File.separator + fileName);

			if (mType != null) {
				headers.setContentType(mType);
			}

			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		} finally {
			in.close();
		}
		return entity;
	}

	// 멀티 미디어 타입 리턴
	// jpg => MediaType.IMAGE_JPEG 리턴
	private MediaType getMediaType(String formatName) {
		if (formatName != null) {
			if (formatName.equals("JPG")) {
				return MediaType.IMAGE_JPEG;
			}

			if (formatName.equals("GIF")) {
				return MediaType.IMAGE_GIF;
			}

			if (formatName.equals("PNG")) {
				return MediaType.IMAGE_PNG;
			}
		}

		return null;
	}
}
