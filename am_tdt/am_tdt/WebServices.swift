//
//  WebServices.swift
//  am_tdt
//
//  Created by Marco Baeza Salazar on 06-11-14.
//  Copyright (c) 2014 USACH. All rights reserved.
//

import Foundation

class Servicios {
    func servicioLogin(){
        var url : String = "http://google.com?test=toto&test2=titi"
        var request : NSMutableURLRequest = NSMutableURLRequest()
        request.URL = NSURL(string: url)
        request.HTTPMethod = "GET"
    
        NSURLConnection.sendAsynchronousRequest(request, queue: NSOperationQueue(), completionHandler:{ (response:NSURLResponse!, data: NSData!, error: NSError!) -> Void in
        
            var error: AutoreleasingUnsafeMutablePointer<NSError?> = nil
        
            let jsonResult: NSDictionary! = NSJSONSerialization.JSONObjectWithData(data, options:NSJSONReadingOptions.MutableContainers, error: error) as? NSDictionary
            
            if jsonResult != nil {
                var i = jsonResult.count
                println(i)
                // process jsonResult
            } else {
                // couldn't load JSON, look at error
            }
        
            
        })
    }
}