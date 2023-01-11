package ForCloud.backend.service;

import ForCloud.backend.dto.Chatting.ChattingResponse;
import ForCloud.backend.dto.Participant.ParticipantResponse;
import ForCloud.backend.entity.Chatting;
import ForCloud.backend.entity.Participant;
import ForCloud.backend.repository.ChattingRepository;
import ForCloud.backend.repository.MemberRepository;
import ForCloud.backend.repository.ParticipantRepository;
import ForCloud.backend.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChattingService {
    private final ChattingRepository chattingRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final ParticipantRepository participantRepository;
    private final Logger logger= LoggerFactory.getLogger(ChattingService.class);

    @Transactional
    public List<ChattingResponse> getChattingList(Long memberId){
        List<Participant> participatingList=participantRepository.findAllByMember_Id(memberId);
        List<ChattingResponse> chattingList=new ArrayList<>();
        for(Participant p:participatingList) {
            Chatting chatting=chattingRepository.findById(p.getChatting().getId()).get();
            List<Participant> participantList=participantRepository.findAllByChatting_Id(chatting.getId());
            List<ParticipantResponse> participantResponse=participantList.stream()
                .map(pl->new ParticipantResponse(pl))
                .collect(toList());
            ChattingResponse res=new ChattingResponse(chatting,participantResponse);
            chattingList.add(res);
        }
        return chattingList;
    }
}
