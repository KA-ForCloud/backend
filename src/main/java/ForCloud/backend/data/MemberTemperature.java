package ForCloud.backend.data;

import ForCloud.backend.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberTemperature {
    private String name;
    private double temperature;

    public MemberTemperature(Member member){
        this.name = member.getName();
        this.temperature = member.getTemperature();
    }
}
