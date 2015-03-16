//
//  SecuenciaEjercicioViewController.swift
//  Creylp
//
//  Created by Marco Baeza on 06-03-15.
//  Copyright (c) 2015 Marco Baeza. All rights reserved.
//

import UIKit
import Alamofire
import Haneke

class SecuenciaEjercicioViewController: UIViewController, UIGestureRecognizerDelegate {
    
    @IBOutlet var imagenPan4: UIPanGestureRecognizer!
    @IBOutlet var imagenPan3: UIPanGestureRecognizer!
    @IBOutlet var imagenPan2: UIPanGestureRecognizer!
    @IBOutlet var imagenPan1: UIPanGestureRecognizer!
    
    @IBOutlet weak var imagenPrimera: UIImageView!
    @IBOutlet weak var imagenCuarta: UIImageView!
    @IBOutlet weak var imagenTercera: UIImageView!
    @IBOutlet weak var imagenSegunda: UIImageView!
    
    @IBOutlet weak var titulo: UILabel!
    
    @IBOutlet weak var imagenNUno: UILabel!
    @IBOutlet weak var imagenNDos: UILabel!
    @IBOutlet weak var imagenNTres: UILabel!
    @IBOutlet weak var imagenNCuatro: UILabel!
    
    
    var secuencia:[String]=[]
    var urlImagenes:[String]=[]
    var idEjercicio:String!
    var idAlumno:String!
    var idUsuario:String!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        var post:NSString = ""
        
        imagenNUno.hidden = true
        imagenNDos.hidden = true
        imagenNTres.hidden = true
        imagenNCuatro.hidden = true
        
       // imagenPrimera.u
        
        NSLog("PostData: %@",post);
        
        var url:NSURL = NSURL(string: "http://localhost:8080/APP_TDT_WEB-war/webresources/ServiciosWeb/ObtenerImagenEjercicio/"+idEjercicio)!
        
        var postData:NSData = post.dataUsingEncoding(NSASCIIStringEncoding)!
        
        var postLength:NSString = String( postData.length )
        
        var request:NSMutableURLRequest = NSMutableURLRequest(URL: url)
        request.HTTPMethod = "GET"
        //request.HTTPBody = postData
        request.setValue(postLength, forHTTPHeaderField: "Content-Length")
        request.setValue("application/x-www-form-urlencoded", forHTTPHeaderField: "Content-Type")
        request.setValue("application/json", forHTTPHeaderField: "Accept")
        
        
        var reponseError: NSError?
        var response: NSURLResponse?
        
        var urlData: NSData? = NSURLConnection.sendSynchronousRequest(request, returningResponse:&response, error:&reponseError)
        
        if ( urlData != nil ) {
            let res = response as NSHTTPURLResponse!;
            
            NSLog("Response code: %ld", res.statusCode);
            
            if (res.statusCode >= 200 && res.statusCode < 300)
            {
                var responseData:NSString = NSString(data:urlData!, encoding:NSUTF8StringEncoding)!
                
                NSLog("Response ==> %@", responseData);
                
                var error: NSError?
                
                let jsonData:NSDictionary = NSJSONSerialization.JSONObjectWithData(urlData!, options:NSJSONReadingOptions.MutableContainers , error: &error) as NSDictionary
                
                
                let success:NSString = jsonData.valueForKey("respuesta")?.valueForKey("codigo")  as NSString
                
                //[jsonData[@"success"] integerValue];
                
                NSLog("Success: " + success);
                
                if(success == "00")
                {
                    NSLog("Imagenes SUCCESS");
                    
                    let nombreTitulo:NSString = jsonData.valueForKey("titulo")?  as NSString
                    
                    self.titulo.text = nombreTitulo;
                    
                    let imagenes:NSArray = jsonData.valueForKey("imagenes")?  as NSArray
                    NSLog("Obtener Ejercicios imagenes SUCCESS");
                    // var ej:NSDictionary = ejercicios[0] as NSDictionary
                    
                    // var valor:NSString = ej.valueForKey("descripcionEjercicio")? as NSString
                    // NSLog("Valor de la descripcion: " +  valor);
                    
                    for( var i = 0;i<imagenes.count;i++ ){
                        
                        var ejercicio:NSDictionary = imagenes[i] as NSDictionary
                        var orden:Int = ejercicio.valueForKey("orden") as Int
                        var urlImagen:String = ejercicio.valueForKey("urlImagen") as String
                        
                        NSLog("Url de imagen: " + urlImagen);
                        urlImagenes.append(urlImagen);
                        secuencia.append(String(orden));
                        
                        let imagenURL:NSURL = NSURL(string: urlImagenes[i].stringByReplacingOccurrencesOfString(" ", withString: "%20"))!;
                        
                        switch i {
                        case 0:
                            imagenPrimera.hnk_setImageFromURL(imagenURL);
                            imagenNUno.hidden = false
                        case 1:
                            imagenSegunda.hnk_setImageFromURL(imagenURL);
                            imagenNDos.hidden = false
                        case 2:
                            imagenTercera.hnk_setImageFromURL(imagenURL);
                            imagenNTres.hidden = false
                        case 3:
                            imagenCuarta.hnk_setImageFromURL(imagenURL);
                            imagenNCuatro.hidden = false
                        default:
                            imagenPrimera.hnk_setImageFromURL(imagenURL);
                        }
                    }
                    
                } else {
                    var error_msg:NSString
                    
                    if success == "99" {
                        error_msg = jsonData.valueForKey("respuesta")?.valueForKey("glosa") as NSString
                    } else {
                        error_msg = "Unknown Error"
                    }
                    var alertView:UIAlertView = UIAlertView()
                    alertView.title = "Sign in Failed!"
                    alertView.message = error_msg
                    alertView.delegate = self
                    alertView.addButtonWithTitle("OK")
                    alertView.show()
                    
                }
                
            } else {
                var alertView:UIAlertView = UIAlertView()
                alertView.title = "Sign in Failed!"
                alertView.message = "Connection Failed"
                alertView.delegate = self
                alertView.addButtonWithTitle("OK")
                alertView.show()
            }
        } else {
            var alertView:UIAlertView = UIAlertView()
            alertView.title = "Sign in Failed!"
            alertView.message = "Connection Failure"
            if let error = reponseError {
                alertView.message = (error.localizedDescription)
            }
            alertView.delegate = self
            alertView.addButtonWithTitle("OK")
            alertView.show()
        }
        
        //1
        let filteredSubviews = self.view.subviews.filter({
            $0.isKindOfClass(UIImageView) })
        
        // 2
        for view in filteredSubviews  {
            // 3
            let recognizer = UITapGestureRecognizer(target: self, action:Selector("handleTap:"))
            // 4
            recognizer.delegate = self
            view.addGestureRecognizer(recognizer)
            
            recognizer.requireGestureRecognizerToFail(imagenPan1)
            recognizer.requireGestureRecognizerToFail(imagenPan2)
            recognizer.requireGestureRecognizerToFail(imagenPan3)
            recognizer.requireGestureRecognizerToFail(imagenPan4)
            //TODO: Add a custom gesture recognizer too
            
            let recognizer2 = TickleGestureRecognizer(target: self, action: Selector("handleTickle:"))
            recognizer2.delegate = self
            view.addGestureRecognizer(recognizer2)
        }
        //self.chompPlayer = self.loadSound("chomp")
        //self.hehePlayer = self.loadSound("hehehe1")

    }

    
     //   NSLog("Response ==> %@", datas.first);
    
       // let urlString = self.datas["images"]["standard_resolution"]["url"]
       // let url = NSURL(string: urlString.stringValue)
       /// self.imagenPrimera.hnk_setImageFromURL(url!)
    
        //NSLog("Url de imagen: " + url);
    

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()

    }

    @IBAction func handlePinch(recognizer : UIPinchGestureRecognizer) {
        recognizer.view!.transform = CGAffineTransformScale(recognizer.view!.transform,
            recognizer.scale, recognizer.scale)
        recognizer.scale = 1
    }
    
    
    @IBAction func handlePan(recognizer:UIPanGestureRecognizer) {
        //comment for panning
        //uncomment for tickling
        //ç return;
        
        let translation = recognizer.translationInView(self.view)
        recognizer.view!.center = CGPoint(x:recognizer.view!.center.x + translation.x,
            y:recognizer.view!.center.y + translation.y)
        recognizer.setTranslation(CGPointZero, inView: self.view)
        
        if recognizer.state == UIGestureRecognizerState.Ended {
            // 1
            let velocity = recognizer.velocityInView(self.view)
            let magnitude = sqrt((velocity.x * velocity.x) + (velocity.y * velocity.y))
            let slideMultiplier = magnitude / 200
            println("magnitude: \(magnitude), slideMultiplier: \(slideMultiplier)")
            
            // 2
            let slideFactor = 0.1 * slideMultiplier     //Increase for more of a slide
            // 3
            var finalPoint = CGPoint(x:recognizer.view!.center.x + (velocity.x * slideFactor),
                y:recognizer.view!.center.y + (velocity.y * slideFactor))
            // 4
            finalPoint.x = min(max(finalPoint.x, 0), self.view.bounds.size.width)
            finalPoint.y = min(max(finalPoint.y, 0), self.view.bounds.size.height)
            
            // 5
            UIView.animateWithDuration(Double(slideFactor * 2),
                delay: 0,
                // 6
                options: UIViewAnimationOptions.CurveEaseOut,
                animations: {recognizer.view!.center = finalPoint },
                completion: nil)
        }
    }

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
