package com.dmall.pms.service.impl.sku;

import com.dmall.common.dto.BaseResult;
import com.dmall.common.dto.ResponsePage;
import com.dmall.common.dto.UploadResult;
import com.dmall.pms.api.dto.sku.request.CheckCreateOrderRequestDTO;
import com.dmall.pms.api.dto.sku.request.PageSkuRequestDTO;
import com.dmall.pms.api.dto.sku.request.StockRequestDTO;
import com.dmall.pms.api.dto.sku.request.UploadRequestDTO;
import com.dmall.pms.api.dto.sku.request.save.SaveSkuAttributeRequestDTO;
import com.dmall.pms.api.dto.sku.request.save.SaveSkuExtRequestDTO;
import com.dmall.pms.api.dto.sku.request.save.SaveSkuMediaRequestDTO;
import com.dmall.pms.api.dto.sku.request.save.SaveSkuRequestDTO;
import com.dmall.pms.api.dto.sku.request.update.UpdateSkuRequestDTO;
import com.dmall.pms.api.dto.sku.response.PageSkuResponseDTO;
import com.dmall.pms.api.dto.sku.response.get.BasicSkuResponseDTO;
import com.dmall.pms.api.dto.sku.response.get.GetSkuResponseDTO;
import com.dmall.pms.api.service.SkuService;
import com.dmall.pms.service.impl.sku.handler.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @description: sku服务实现
 * @author: created by hang.yu on 2019-12-16 15:14:50
 */
@RestController
public class SkuServiceImpl implements SkuService {

    @Autowired
    private SaveOrUpdateSkuHandler saveOrUpdateSkuHandler;

    @Autowired
    private UpdateSkuHandler updateSkuHandler;

    @Autowired
    private GetSkuHandler getSkuHandler;

    @Autowired
    private PageSkuHandler pageSkuHandler;

    @Autowired
    private SaveSkuAttributeHandler saveSkuAttributeHandler;

    @Autowired
    private SaveSkuExtHandler saveSkuExtHandler;

    @Autowired
    private UploadSkuHandler uploadSkuHandler;

    @Autowired
    private SaveMediaHandler saveMediaHandler;

    @Autowired
    private OnPublishHandler onPublishHandler;

    @Autowired
    private OffPublishHandler offPublishHandler;

    @Autowired
    private GetBasicSkuHandler getBasicSkuHandler;

    @Autowired
    private CreateOrderCheckHandler createOrderCheckHandler;

    @Autowired
    private LockStockHandler lockStockHandler;

    @Autowired
    private OutStockHandler outStockHandler;

    @Autowired
    private UnLockStockHandler unLockStockHandler;

    @Override
    public BaseResult<Long> saveOrUpdate(@RequestBody SaveSkuRequestDTO requestDTO) {
        return saveOrUpdateSkuHandler.handler(requestDTO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResult<Long> saveSkuAttributeValue(@RequestBody SaveSkuAttributeRequestDTO requestDTO) {
        return saveSkuAttributeHandler.handler(requestDTO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResult<Long> saveSkuExt(@RequestBody SaveSkuExtRequestDTO requestDTO) {
        return saveSkuExtHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<UploadResult> upload(Long id, MultipartFile file) {
        return uploadSkuHandler.handler(new UploadRequestDTO(id, new MultipartFile[] {file}));
    }

    @Override
    public BaseResult<UploadResult> batchUpload(Long id, @NotNull(message = "sku图片数组不能为空") MultipartFile[] files) {
        return uploadSkuHandler.handler(new UploadRequestDTO(id, files));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResult<Long> saveSkuMedia(@RequestBody SaveSkuMediaRequestDTO requestDTO) {
        return saveMediaHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<Long> onPublish(Long id) {
        return onPublishHandler.handler(id);
    }

    @Override
    public BaseResult<Long> offPublish(Long id) {
        return offPublishHandler.handler(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResult<Long> update(@RequestBody UpdateSkuRequestDTO requestDTO) {
        return updateSkuHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<GetSkuResponseDTO> get(Long id) {
        return getSkuHandler.handler(id);
    }

    @Override
    public BaseResult<ResponsePage<PageSkuResponseDTO>> page(@RequestBody PageSkuRequestDTO requestDTO) {
        return pageSkuHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<List<BasicSkuResponseDTO>> getBasic(List<Long> ids) {
        return getBasicSkuHandler.handler(ids);
    }

    @Override
    public BaseResult<List<BasicSkuResponseDTO>> createOrderCheck(@RequestBody CheckCreateOrderRequestDTO requestDTO) {
        return createOrderCheckHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<Void> lockStock(@RequestBody StockRequestDTO requestDTO) {
        return lockStockHandler.handler(requestDTO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResult<Void> outStock(@RequestBody StockRequestDTO requestDTO) {
        return outStockHandler.handler(requestDTO);
    }

    @Override
    public BaseResult<Void> unLockStock(@Valid StockRequestDTO requestDTO) {
        return unLockStockHandler.handler(requestDTO);
    }

}
