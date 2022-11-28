package fidelity.clase6;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class baseReq {
    
    private String hostID;
    private String operationID;
    private String clientIpAddress;
    private String code;

}
