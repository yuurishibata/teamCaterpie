public class User{

    private String name = "MR_DEFAULT_X";
    private final static int MAN = 1;
    private final static int WOMAN = 2;
    private int sex = MAN;//default
    private int location = 0;

    public User(){
    
    }

    public boolean isMan(){
    
	if(sex ==MAN){
	    return true;
	}else{
	    return false;
	}


    }

    public boolean isWoman(){
    
	if(sex ==WOMAN){
	    return true;
	}  else {
	    
	    return false;
	}
    
    }

    public void print(){

	System.out.println("Name : "+this.name);
	System.out.println("Man : " +isMan());
	System.out.println("Locatin : "+this.location);
    
    }


}
