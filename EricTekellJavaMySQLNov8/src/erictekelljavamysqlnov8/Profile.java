
package erictekelljavamysqlnov8;

import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Date;


public class Profile {
    
    public int profileId;
    public String name;
    public Date birthday;
    public BigDecimal weight;
    public boolean gender;
    public String classType;
    public String race;
    public Blob img;
    
    
}
