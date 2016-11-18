
///////////////////////////////////////////////////////////////////////////////
// main entry-point
///////////////////////////////////////////////////////////////////////////////
document.addEventListener("DOMContentLoaded", function(e)
{
    // HTML page is loaded at this monent
    Logger.open();
    log("HTML page is loaded.");

    // intialize DOM elements
    init();

    // display the previous wish list when HTML is first loaded
    // if you call addWish() with null/empty string, it returns the prev. wishes
    log("Get the previous wish list.");
    addWish(null);
});



///////////////////////////////////////////////////////////////////////////////
// init DOM elements
///////////////////////////////////////////////////////////////////////////////
function init()
{
    log("Initialize DOM emements.");

    // text input field
    var textWish = document.getElementById("textWish");
    if(!textWish)
        textWish = { value:"" }; // empty string if not found

    // register click event
    var buttonAdd = document.getElementById("buttonAdd");
    if(buttonAdd)
    {
        buttonAdd.addEventListener("click", function()
        {
            log("buttonAdd is clicked.");
            // get the text from <input> and pass it as request param
            addWish(textWish.value);
        });
    }

    // register click event
    var buttonClear = document.getElementById("buttonClear");
    if(buttonClear)
    {
        buttonClear.addEventListener("click", function()
        {
            log("buttonClear is clicked.");
            // reset wish list
            clearWishList();
            textWish.value = "";
        });
    }
}


///////////////////////////////////////////////////////////////////////////////
// add new wish item to the list
// After request WishList, update the wish list in <ol>
///////////////////////////////////////////////////////////////////////////////
function addWish(item)
{
    log("Try to add new wish, " + item + "...");

    // encode special chars
    var param = "wish=" + encodeURIComponent(item || "");
    log("  param: " + param);

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "WishList", true);

    // set proper request header for POST
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

    xhr.send(param);
    xhr.onload = function(e)
    {
        if(xhr.status == 200)
        {
            log("Success to add new wish.");
            log("Raw JSON: " + xhr.response);

            // convert response to JS array then update the wish list in <ol>
            // NOTE: if JSON.parse() failed, it throws exception
            try
            {
                var wishes = JSON.parse(xhr.response);
                updateWishList(wishes);
//                var list = document.getElementById("wishList");
//                 list.innerHTML = ""; 
//                  for(var i = 0; i < wishes.data.length; ++i)
//                   {
//                  list.innerHTML += "<li>" + wishes.data[i] + "</li>";
//                   }

            }
            catch(e)
            {
                log("[ERROR] Failed to parse JSON: " + e);
            }
        }
        else
        {
            log("[ERROR] Failed to request WishList servlet: " + xhr.status);
        }
    };
}



///////////////////////////////////////////////////////////////////////////////
// reset the current wish list
// After request WishList, reset the wish list in <ol>
///////////////////////////////////////////////////////////////////////////////
function clearWishList()
{
    log("Try to clear wish list...");

    var param = "clear=true";
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "WishList", true);

    // set proper header for POST
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

    xhr.send(param);
    xhr.onload = function(e)
    {
        if(xhr.status == 200)
        {
            log("Success to clear wish list.");

            // clear wish list with an empty array
            updateWishList([]);
        }
        else
        {
            log("[ERROR] Failed to request WishList servlet: " + xhr.status);
        }
    };
}



///////////////////////////////////////////////////////////////////////////////
// write <li> emements in <ol> with given array input argument
///////////////////////////////////////////////////////////////////////////////
function updateWishList(wishes)
{
    log("Update wish list to <ol>.");

    // validate arguement
    wishes = wishes || [];  // if null, use empty array
    log("Wishes to display: " + wishes);

    var list = document.getElementById("wishList");
    list.innerHTML = "";    // reset to empty

    for(var i = 0; i < wishes.length; ++i)
    {
        list.innerHTML += "<li>" + wishes[i] + "</li>";
    }

    log();  // print blank line
}
