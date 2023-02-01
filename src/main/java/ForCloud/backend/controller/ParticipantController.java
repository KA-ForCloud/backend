package ForCloud.backend.controller;

import ForCloud.backend.config.BaseResponse;
import ForCloud.backend.dto.Participant.GetLastReadResponse;
import ForCloud.backend.service.ParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@ResponseBody
@RequiredArgsConstructor
public class ParticipantController {
    private final ParticipantService participantService;

    /**
     * 참여자가 마지막으로 읽은 채팅 메세지 업데이트
     * @param memberId
     * @param roomId
     * @param lastId
     * @return
     */
    @PatchMapping("/api/member/{memberId}/rooms/{roomId}/{chattingId}")
    public BaseResponse<String> updateLastChat(@PathVariable(name="memberId")Long memberId, @PathVariable(name="roomId")Long roomId, @PathVariable(name="chattingId")Long lastId){
        String response=participantService.updateLastRead(memberId,roomId,lastId);

        return new BaseResponse<>(response);
    }

    /**
     * 참여자가 채팅방 나가기
     * @param memberId
     * @param roomId
     * @return
     */
    @DeleteMapping("/api/member/{memberId}/rooms/{roomId}/participant")
    public BaseResponse<String> deleteParticipant(@PathVariable(name="memberId")Long memberId,@PathVariable(name="roomId")Long roomId){
        String response=participantService.deleteParticipant(memberId,roomId);

        return new BaseResponse<>(response);
    }

    /**
     * 마지막으로 읽은 메세지 조회
     * @param memberId
     * @param roomId
     * @return
     */
    @GetMapping("/api/member/{memberId}/rooms/{roomId}")
    public BaseResponse<GetLastReadResponse> getLastRead(@PathVariable(name="memberId")Long memberId, @PathVariable(name="roomId")Long roomId){
        GetLastReadResponse response=participantService.getLastRead(memberId,roomId);

        return new BaseResponse<>(response);
    }
}
