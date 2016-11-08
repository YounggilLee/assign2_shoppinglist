/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gil;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author yglee
 */
public class WishList extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       //for storing wishlist
       Cookie cookie = null;
       //get cookie array which is from client side
       Cookie[] cookies = request.getCookies();
       if(cookies != null){
           //find cookie
           for (int i = 0; i < cookies.length; ++i) {
               if(cookies[i].getName().equals("wishList")){
                   // found it!
                   cookie = cookies[i];
                   break;
               }
           }
       }
       
       // debug
        System.out.println(cookie);

        //get req params
       String wish = request.getParameter("wish");
       String clear = request.getParameter("clear");
       
       
       
       //if clear was set, expire cokkie
       if(clear != null){
           
       }else{
             //if new wish item was set, append it to the current list
             if(wish != null && !wish.isEmpty()){
                 
             }else {
             //no wish item, get the current wish list only
             }
       }
       
     
           
    }


}
