/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.model;

/**
 *
 * @author ferha
 */
public class Role extends CommonModel{
    
    private String roleName;
    
    public String getRoleName(){
        return roleName;
    }
    
    public void setRoleName(String roleName){
        this.roleName = roleName;
    }
    
    @Override
    public String toString(){
        return "Role{"+"roleName"+ roleName + '}';
    }
    
}
