package com.dmall.pms.mock.service;
import java.math.BigDecimal;

import com.dmall.pms.mock.feign.MemberFeignService;
import com.dmall.mms.api.dto.member.request.MemberRequestDTO;
import com.dmall.mms.api.dto.member.response.MemberResponseDTO;
import com.dmall.pms.mock.api.service.product.ProductService;
import com.dmall.pms.mock.api.dto.product.request.ProductRequestDTO;
import com.dmall.pms.mock.api.dto.product.response.ProductResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 商品服务实现
 * @author: created by hang.yu on 2019/10/14 22:10
 */
@RestController(value = "mockProductServiceImpl")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private MemberFeignService memberFeignService;

//    @Value("${product.name}")
    private String productName = "小米9";

    @Override
    public ProductResponseDTO getProduct(@RequestBody ProductRequestDTO requestDTO) {
        ProductResponseDTO responseDTO = new ProductResponseDTO();
        responseDTO.setId(requestDTO.getId());
        responseDTO.setProductNo(requestDTO.getProductNo());
        responseDTO.setName(productName);
        responseDTO.setPrice(BigDecimal.ONE);

        MemberRequestDTO memberRequestDTO = new MemberRequestDTO();
        memberRequestDTO.setId(1L);
        memberRequestDTO.setName(memberRequestDTO.getName());

        MemberResponseDTO memberResponseDTO = memberFeignService.getMember(memberRequestDTO);
        responseDTO.setMemberResponseDTO(memberResponseDTO);
        return responseDTO;
    }
}
