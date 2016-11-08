/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gil;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
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
