package com.oracle.OMG.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oracle.OMG.dto.Customer;
import com.oracle.OMG.dto.Member;
import com.oracle.OMG.service.thService.Paging;
import com.oracle.OMG.service.yaService.YaCustomerService;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Data
@Slf4j
@RequiredArgsConstructor
public class YaController {
	private final YaCustomerService ycs;
	//거래처 전체조회
	@RequestMapping(value="/customerList")
	public String customerList(Customer customer, Model model, String currentPage) {
	System.out.println("YaController customerList start...");
	
		int totalCustomer = 0;
		totalCustomer = ycs.totalCustomer(customer);
		System.out.println("YaContorller totalCustomer->"+ totalCustomer);
		
		Paging custPage = new Paging(totalCustomer, currentPage, 10);
		customer.setStart(custPage.getStart());
		customer.setEnd(custPage.getEnd());
		
		List<Customer>customerList = null;
		customerList = ycs.customerList(customer);
		
		
		model.addAttribute("totalCustomer", totalCustomer);
		model.addAttribute("custPage", custPage);
		model.addAttribute("customerList", customerList);
		
		
	return "ya/customer";	
	}
	
	//거래처 상세보기
	@GetMapping("/customerDetail")
	@ResponseBody
	public Map<String, Object> customerDetail(@RequestParam("custcode")int custcode) {
		System.out.println("YaController Start customerDetail start...");
		
		Map<String, Object> result = new HashMap<>();
		
		Customer customer = ycs.customerDetail(custcode);
		System.out.println("YaConroller detailCustomer custcode:"+customer.getCustcode());
		System.out.println("YaConroller detailCustomer company:"+customer.getCompany());	
		
		result.put("customer", customer);
		return result;
	}
	
	//직원 전체조회 ( 거래처수정용) 미완 
	@PostMapping("/memberList")
	@ResponseBody
	public Map<String, Object> memberList(@RequestBody Member member){
		Map<String, Object> result = new HashMap<>();
		
		List<Member> memberList = null;
		memberList = ycs.memberList(member);
		result.put("memberList", memberList);
	
		return result;
	}
	
	
	//거래처 정보수정
	@PostMapping("/updateCustomer")
	@ResponseBody
	public Map<String,Object> updateCustomer(@RequestBody Customer customer){
		 Map<String, Object> result = new HashMap<>();
		 
		 customer =  ycs.updateCustomer(customer);		  
		 result.put("success", true);
			
		return result;
				
	}
	
	//거래처 등록
	@RequestMapping(value="/insertCustomer")
	@ResponseBody
	public Map<String, Object> insertCustomer(@RequestBody Customer customer){
		 Map<String, Object> result = new HashMap<>();
		 
		 customer = ycs.insertCustomer(customer);
		 result.put("success", true);
		 
		 
		return result;
		 
	}
	
	//거래처삭제
	@GetMapping("/deleteCustomer")
	public String deleteCustomer(int custcode, Model model) {
		int deleteResult = ycs.deleteCustomer(custcode);
		System.out.println("YaController ycs.deleteCustomer start...");
		
		return "redirect:customerList";
	}
	
	//거래처 검색
	@GetMapping("/customerSearch")
	@ResponseBody
	public Map<String, Object> customerSearch(HttpServletRequest request){
		System.out.println("YaController ycs.customerSearch Start...");
		String keyword=request.getParameter("keyword");
		String currentPage = request.getParameter("currentPage");
		
		System.out.println("kewyord?:"+keyword);
		
		
		int totalSearch = ycs.totalSearch(keyword);
		System.out.println("YaController totalSearch(keyword):"+totalSearch);
					
		Paging paging = new Paging(totalSearch, currentPage);
		List<Customer> customerSearchList = ycs.customerSearch(keyword,paging.getStart(), paging.getEnd());
		
		Map<String, Object> result = new HashMap<>();
		result.put("customerSearchList", customerSearchList);
		result.put("paging",paging);
		
		return result;
	}
	
	//거래처판매실적 조회
	@GetMapping("/customerSales")
	/* @ResponseBody */
	/*public Map<String, Object> customerSalesList (Customer customer ){*/
	public String customerSalesList(Customer customer, Model model) {
		System.out.println("YaController ycs.custoemrSales start...");
		List<Customer> customerSalesList = ycs.customerSalesList(customer);
		model.addAttribute("customerSalesList", customerSalesList);
		/*
		 * Map<String, Object> result = new HashMap<>(); result.put("customerSalesList",
		 * customerSalesList); return result;
		 */
		//날짜변환		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		for (Customer c : customerSalesList) {
		    try {
		        Date date = sdf.parse(c.getPurDate());
		        c.setDate(date);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		
		 return "ya/salesByCustomer";
	}
}
