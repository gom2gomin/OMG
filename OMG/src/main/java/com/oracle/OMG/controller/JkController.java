package com.oracle.OMG.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.oracle.OMG.dto.Customer;
import com.oracle.OMG.dto.PurDetail;
import com.oracle.OMG.dto.Purchase;
import com.oracle.OMG.dto.Warehouse;
import com.oracle.OMG.service.chService.ChCustService;
import com.oracle.OMG.service.chService.ChPurService;
import com.oracle.OMG.service.chService.Paging;
import com.oracle.OMG.service.jkService.JkWareService;
import com.oracle.OMG.service.yrService.YrItemService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Controller
@Data
@Slf4j
public class JkController {
	private static final Logger logger = LoggerFactory.getLogger(JkController.class);
	private final JkWareService jws;
	private final YrItemService	yis;
	private final ChCustService	ccs;
	private final ChPurService	cps;
	
	// 기초재고등록
	@RequestMapping(value="/invRegister")
	public String invRegister(@RequestParam(value = "monthSelect1", required = false)  String selectedMonth, HttpSession session, Warehouse warehouse, Model model) {
	    System.out.println("JkController invRegister start...");

	    
	    // 클라이언트에서 전송된 월 값의 형식 변경 (YYYYMM 형식으로)
	    if (selectedMonth != null) {
	        selectedMonth = selectedMonth.replace("-", "");
	        warehouse.setYm(selectedMonth);
	        System.out.println("selectedMonth: " + selectedMonth);
	    }
	        System.out.println("selectedMonth"+selectedMonth);
	    
	    try {
	        int result = jws.insertInv(warehouse);
	        System.out.println("result: " + result);

	        // 성공적으로 처리된 경우
	        model.addAttribute("successMessage", "정상적으로 등록되었습니다."); 
	        System.out.println("model"+model);
	        return "jk/invRegister";
	    } catch (DuplicateKeyException e) {
	        // 중복 등록 오류가 발생한 경우
	        String errorMessage = "재고 등록에 실패했습니다. 이미 등록된 기초재고가 있습니다.";
	        model.addAttribute("errorMessage", errorMessage);
	        e.printStackTrace();
	        // 사용자에게 오류 메시지를 보여줄 수 있는 페이지로 이동
	        return "errorPage";
	    } catch (Exception e) {
	        // 기타 오류 처리
	        throw new RuntimeException("서버 오류가 발생했습니다.", e);
	    }
	}

	// 기초재고 조정
	@RequestMapping(value="/updateInv")
	public ResponseEntity<String> updateInv(@RequestParam(value = "monthSelect2", required = false) String selectedMonth,
	                        @RequestParam(value = "code") int code,
	                        @RequestParam(value = "cnt") int cnt,
	                        Warehouse warehouse) {
	    System.out.println("JkController updateInv start...");

	    // 클라이언트에서 전송된 월 값의 형식 변경 (YYYYMM 형식으로)
	    if (selectedMonth != null) {
	        selectedMonth = selectedMonth.replace("-", "");
	        warehouse.setYm(selectedMonth);
	        System.out.println("selectedMonth: " + selectedMonth);
	    }

	    try {
	    	 int result = jws.updateInv(warehouse);
		     System.out.println("result: " + result);

		   // 성공적으로 처리된 경우
		     return ResponseEntity.ok("정상적으로 수정되었습니다.");
	   
	    } catch (Exception e) {
	        // 오류 처리
	        e.printStackTrace();
	        // 오류가 발생한 경우
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("삭제 중 오류가 발생했습니다.");
	    }
	}

	// 기초재고 삭제
	@RequestMapping(value="/deleteInv")
	public ResponseEntity<String> deleteInv(@RequestParam(value = "monthSelect2", required = false) String selectedMonth,
	                        @RequestParam(value = "code") int code, Model model,
	                        Warehouse warehouse) {
	    System.out.println("JkController deleteInv start...");
	
	    if (selectedMonth != null) {
	        selectedMonth = selectedMonth.replace("-", "");
	        warehouse.setYm(selectedMonth);
	        System.out.println("selectedMonth: " + selectedMonth);
	    }

	    try {
	        int result = jws.deleteInv(warehouse);
	        System.out.println("result: " + result);

	        // 성공적으로 처리된 경우
	        return ResponseEntity.ok("정상적으로 삭제되었습니다.");

	    } catch (Exception e) {
	        // 오류 처리
	        e.printStackTrace();
	        // 오류가 발생한 경우
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("삭제 중 오류가 발생했습니다.");
	    }
	}

		
	// 제품정보 조회(업데이트용)
	@GetMapping("/getItemDetails")
	@ResponseBody
	public Map<String, Object> getItemDetails(@RequestParam("code") int code, @RequestParam("ym") String ym ) {
	    logger.info("Fetching item details for product code: {}", code, "ym",ym);

	    Map<String, Object> response = jws.selectItem(code, ym);
	    
	    logger.info("Response: {}", response);
	    return response;
	}

	// 제품정보 조회(등록용)
	@GetMapping("/getItemDetails2")
	@ResponseBody
	public Map<String, Object> getItemDetails2(@RequestParam("code") int code ) {
	    logger.info("Fetching item details for product code: {}", code);

	    Map<String, Object> response = jws.selectItem2(code);
	    
	    logger.info("Response: {}", response);
	    return response;
	}

	// 발주 조회
	@RequestMapping(value="/inboundRegister")
	public String invPurList(Purchase purchase, String currentPage, Model model) {
	    System.out.println("JkController invPurList start....");
	 	    
	    int totalPur = 0;
		List<Purchase> purList = null;
		Paging page = null;
		System.out.println("ChController purList Start...");
		// 검색(custcode가 있을 때 )
		if(purchase.getCustcode() >0) {
			model.addAttribute("srchCompany", purchase.getCustcode());
		}
		//검색(날짜가 있을 때)
		if(purchase.getPur_date() != null) {
			String purDate = purchase.getPur_date();
			DateTimeFormatter formmater = DateTimeFormatter.ofPattern("yy/MM/dd");
			LocalDate ldt = LocalDate.parse(purDate, formmater);
			model.addAttribute("srchDate", ldt);
		}
		
		totalPur = cps.totalPur(purchase);
		page = new Paging(totalPur, currentPage);
		
		purchase.setStart(page.getStart());
		purchase.setEnd(page.getEnd());	
		
		//발주서 전체 리스트
		purList = cps.purList(purchase);
		System.out.println("purList: " + purList);
		// 발주서 리스트 소환 성공시 
		if(purList != null) {
			// purList 조회 성공 시
			for(Purchase p: purList) {
				// 제품 종류			총수량		총가격(전체 가격)
				int totalType = 0; int totalPrice = 0;
				
				// 발주서 상세 내용 불러오기 
				List<PurDetail> pd = cps.purDList(p);
				totalType = pd.size(); // row 수 = 발주서 내 물품 수
				System.out.println();
				// 상세 내용의 물품 항목별 수량과 결제액 
				for(PurDetail pd2 : pd) {
					totalPrice += pd2.getQty() * pd2.getPrice();
				}
				p.setTotalType(totalType);
				p.setTotalQty(pd.stream().mapToInt(m->m.getQty()).sum());
				p.setTotalPrice(totalPrice);
			}
		}
		List<Customer> pur_custList = ccs.custList();
		
		model.addAttribute("pur_custList", pur_custList);
		model.addAttribute("purList",purList);
		model.addAttribute("totalPur",totalPur);
		model.addAttribute("page",page);
		
		System.out.println("model"+model);
	    return "jk/inboundRegister";
		
		
	}
//	 List<Purchase> purMonthData = jws.purMonthData(month);	
	// 입고등록
//	@RequestMapping(value="/inboundRegister")
//	public String inboundRegister(Model model, Purchase purchase) {
//		System.out.println("JkController inboundRegister start...");
//		
//		return "jk/inboundRegister";
//		}
	
	
	// 월별 재고리스트 조회

	@GetMapping("/monthData")
	@ResponseBody
	public List<Warehouse> monthData(@RequestParam(name = "month") String month, @RequestParam(name = "invType", required = false) String invType) {
	    System.out.println("JkController monthData start....");
	    logger.info("Received month: {}", month);
	    logger.info("Received invType: {}", invType);

	    Map<String, String> params = new HashMap<>();
	    params.put("month", month);
	    params.put("invType", invType);

	    List<Warehouse> monthData = jws.monthData(params);
	    
	    logger.info("JkController monthData.size(): {}", monthData.size());

	    return monthData;
	}


	// 월별 입고리스트 조회
	@GetMapping("/getIOData")
	@ResponseBody
	public List<Warehouse> getIOData(@RequestParam(name = "month") String month,@RequestParam(name = "IO_Type", required = false) String IO_Type) {
	    System.out.println("JkController getIOData start....");
	    logger.info("Received month: {}", month);
	    logger.info("Received IO_Type: {}", IO_Type);

	    Map<String, String> params = new HashMap<>();
	    params.put("month", month);
	    params.put("IO_Type", IO_Type);
	    
	    List<Warehouse> getPurchaseData = jws.getPurchaseData(params);
	    
	    logger.info("JkController getIOData.size(): {}", getPurchaseData.size());

	    return getPurchaseData;
	}

	
//	// 월별 입고리스트 조회
//	@GetMapping("/getIOData")
//	@ResponseBody
//	public List<Warehouse> getIOData(
//	        @RequestParam(name = "month") String month,
//	        @RequestParam(name = "IO_Type", required = false) String IO_Type) {
//	    System.out.println("JkController getIOData start....");
//	    logger.info("Received month: {}", month);
//	    logger.info("Received IO_Type: {}", IO_Type);
//
//	    List<Warehouse> getIOData;
//	    if ("INBOUND".equals(IO_Type)) {
//	        // 호출할 구매 데이터에 대한 매퍼 사용
//	    	getIOData = jws.getPurchaseData(month);
//	    	getIOData = jws.getPurchaseDataResultMap(month, "resultmap01");
//	    } else if ("OUTBOUND".equals(IO_Type)) {
//	        // 호출할 판매 데이터에 대한 매퍼 사용
//	        getIOData = jws.getSalesData(month);
//	    } else {
//	        // 기본적으로는 두 데이터를 합친 매퍼 사용
//	        getIOData = jws.getIOData(month);
//	    }
//	    
//	    logger.info("JkController getIOData.size(): {}", getIOData.size());
//
//	    return getIOData;
//	}

	
@ControllerAdvice
public class GlobalExceptionHandler {
  // 예외 처리 핸들러
	 @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	    public ResponseEntity<String> handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e) {
	        String errorMessage = "재고 등록에 실패했습니다. 이미 등록된 기초재고가 있습니다.";
	        return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT); // 409 Conflict 상태 코드 사용
	    }
}
}
