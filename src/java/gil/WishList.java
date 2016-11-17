/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gil;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author yglee
 */
@WebServlet("/WishList")
public class WishList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //prepare output
       response.setContentType("application/json");     
        PrintWriter out = response.getWriter();

        //for storing wishlist
        Cookie cookie = null;

        //get cookie array which is from client side
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {

            //find cookie
            for (int i = 0; i < cookies.length; ++i) {
                if (cookies[i].getName().equals("wishList")) {

                    // found it!
                    cookie = cookies[i];
                    // debug
                   // out.println(cookie.getValue());
                   
                                     
                    break;
                }
            }
        }
        //get req params from user
        String wish = request.getParameter("wish");
        String clear = request.getParameter("clear");

        //if clear was set, expire cokkie
        if (clear != null) {

            //for checking null when the use push clear button mutiple time
            if (cookie != null) {
                //out.println("clear cookie");

                //expire th cookie immediately  
                cookie.setMaxAge(0);

                // the server has to tell client the cookie is expired
                response.addCookie(cookie);
            }
        } else {
            // temp var to store cookie value
            String wishList = null;

            //if new wish item was set, append it to the current list
            if (wish != null && !wish.isEmpty()) {

                //first time to add wish item to cookie, create one
                if (cookie == null) {
                    cookie = new Cookie("wishList", wish);

                    wishList = wish;
                                       
                } else {

                    // append new item to existing wish list
                    //first, get the previous cookie value
                    wishList = cookie.getValue();

                    // 2nd, add new wish with delimiter (~)
                    wishList += "~" + wish;

                    //must update cookie value
                    cookie.setValue(wishList);
  
                    String[] lists = wishList.split("~");
                                                        
                    String json = "{" + "\"data\":[" + "\""+ lists[0] +"\"";                    
                        for (int i = 1; i < lists.length; ++i) {                          
                            
                            json +=   ",\""+ lists[i] +"\"";
                                }
                        json += "]}";
                        
                      out.print(json);
                     
                        
                     
                    //debug
                   // out.println("New Cookie value: " + wishList);

                    //String[] lists = wishList.split("~");
                    
//                    JSONObject jObject = new JSONObject();
//                    JSONArray jArray = new JSONArray();
//
//                    try {
//                        for (int i = 0; i < lists.length; ++i) {
//                            jArray.add(lists[i]);
//                        }
//                        jObject.put("data", jArray);
//                    } catch (Exception e) {
//                        log("Data json err : " + e.getMessage());
//                    }
//
//                   String json = jObject.toString();

                           
// "{"  + "  \"data\": ["  + "  {" + "  \"lists[i]\": \"1\" + " }" + "  ]"  + "}"
                 
                   
                }
                //update cookie object //set time 48 hours
                cookie.setMaxAge(48 * 60 * 60);
                response.addCookie(cookie);

                //no wish item, get the current wish list only
            } else {
                if (cookie != null) {

                    //get the prev. wish list
                    wishList = cookie.getValue();

                    //for (int i = 0; i < lists.length; ++i) {
                    // json = "\"lists\":[\"" + lists[0] + "\"]" ;
                    // }         
                    //out.println("Current Cookie value: " + json);
                }
            }
        }

    }

}
