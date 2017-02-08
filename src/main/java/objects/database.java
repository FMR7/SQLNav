package objects;

/**
 *
 * @author Fernando
 */
public class database {
    private String name;

    public database(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
