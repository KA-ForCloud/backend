package ForCloud.backend.data;

import ForCloud.backend.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberTemperature {
    private String name;
    private double temperature;

    public MemberTemperature(User user){
        this.name = user.getUser_name();
        this.temperature = user.getTemperature();
    }
}
