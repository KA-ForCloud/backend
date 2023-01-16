//package ForCloud.backend.controller;
//
//import ForCloud.backend.config.BaseResponse;
//import ForCloud.backend.dto.Chatting.ChattingResponse;
//import ForCloud.backend.service.ChattingService;
//import lombok.RequiredArgsConstructor;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequiredArgsConstructor
//public class ChattingController {
//
//    private final ChattingService chattingService;
//    private final Logger logger= LoggerFactory.getLogger(ChattingController.class);
//
//
//    @GetMapping("/api/member/{memberId}/rooms")
//    public BaseResponse<List<ChattingResponse>> getChattingList(@PathVariable(name="memberId") Long memberId){
//        List<ChattingResponse> response=chattingService.getChattingList(memberId);
//        return new BaseResponse<>(response);
//    }
//
//}
