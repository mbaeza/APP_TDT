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
    
    
    @IBOutlet weak var ticketCuatro: UIImageView!
    @IBOutlet weak var ticketTres: UIImageView!
    @IBOutlet weak var ticketDos: UIImageView!
    @IBOutlet weak var ticketUno: UIImageView!
    
    @IBOutlet var imagenPan4: UIPanGestureRecognizer!
    @IBOutlet var imagenPan3: UIPanGestureRecognizer!
    @IBOutlet var imagenPan2: UIPanGestureRecognizer!
    @IBOutlet var imagenPan1: UIPanGestureRecognizer!
    
    @IBOutlet weak var imagenPrimera: UIImageView!
    @IBOutlet weak var imagenCuarta: UIImageView!
    @IBOutlet weak var imagenTercera: UIImageView!
    @IBOutlet weak var imagenSegunda: UIImageView!
    
    @IBOutlet weak var titulo: UILabel!
    
    
    @IBOutlet weak var imagenNUno: UIImageView!
    @IBOutlet weak var imagenNDos: UIImageView!
    @IBOutlet weak var imagenNCuatro: UIImageView!
    @IBOutlet weak var imagenNTres: UIImageView!
    
    
    var secuencia:[String]=[]
    var imagenesSuperior:[UIImageView]=[]
    var imagenesInferior:[UIImageView]=[]
    var urlImagenes:[String]=[]
    var idEjercicio:String!
    var idAlumno:String!
    var idUsuario:String!
    
    var imageSupSelecc: UIImageView!
    var imageInfSelecc: UIImageView!
    
    var puntoInicialImagen:[CGPoint] = []
    var puntoInicialSup:CGPoint!

    override func viewDidLoad() {
        super.viewDidLoad()

        var post:NSString = ""
        
        puntoInicialImagen.append(imagenPrimera.center);
        puntoInicialImagen.append(imagenSegunda.center);
        puntoInicialImagen.append(imagenTercera.center);
        puntoInicialImagen.append(imagenCuarta.center);

        //imagenNUno.bringSubviewToFront(imagenPrimera)
        
        imagenNUno.hidden = true
        imagenNDos.hidden = true
        imagenNTres.hidden = true
        imagenNCuatro.hidden = true
        
        imagenNUno.tag = 0
        imagenNDos.tag = 1
        imagenNTres.tag = 2
        imagenNCuatro.tag = 3
        
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
                        
                        NSLog("Url de imagen: " + String(orden));
                        urlImagenes.append(urlImagen);
                        secuencia.append(String(orden));
                        
                        let imagenURL:NSURL = NSURL(string: urlImagenes[i].stringByReplacingOccurrencesOfString(" ", withString: "%20"))!;
                        
                        switch i {
                        case 0:
                            imagenPrimera.hnk_setImageFromURL(imagenURL);
                            imagenPrimera.tag = orden
                            imagenNUno.hidden = false
                            imagenesSuperior.append(imagenPrimera)
                            imagenesInferior.append(imagenNUno)
                        case 1:
                            imagenSegunda.hnk_setImageFromURL(imagenURL);
                            imagenSegunda.tag = orden
                            imagenNDos.hidden = false
                            imagenesSuperior.append(imagenSegunda)
                             imagenesInferior.append(imagenNDos)
                        case 2:
                            imagenTercera.hnk_setImageFromURL(imagenURL);
                            imagenTercera.tag = orden
                            imagenNTres.hidden = false
                            imagenesSuperior.append(imagenTercera)
                            imagenesInferior.append(imagenNTres)
                        case 3:
                            imagenCuarta.hnk_setImageFromURL(imagenURL);
                            imagenCuarta.tag = orden
                            imagenNCuatro.hidden = false
                            imagenesSuperior.append(imagenCuarta)
                            imagenesInferior.append(imagenNCuatro)
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
        
      /*  //1
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
        //self.hehePlayer = self.loadSound("hehehe1")*/

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
        recognizer.view!.center = CGPoint(x:recognizer.view!.center.x + translation.x,y:recognizer.view!.center.y + translation.y)
        recognizer.setTranslation(CGPointZero, inView: self.view)
        
       
        if recognizer.state == UIGestureRecognizerState.Began {
            let imagenSeleccionada1 = recognizer.view as UIImageView
            puntoInicialSup = imagenSeleccionada1.center
            
            self.view.bringSubviewToFront(imagenSeleccionada1)
        }
        
        if recognizer.state == UIGestureRecognizerState.Changed {
            let filteredSubviews = self.view.subviews.filter({
                $0.isKindOfClass(UIImageView) })
            
            let imagenSeleccionada = recognizer.view as UIImageView
            //puntoInicialSup = imagenSeleccionada.center
            
            //for imageS in imagenesSuperior {
                for imageI in imagenesInferior {
                    if(CGRectIntersectsRect(imagenSeleccionada.frame, imageI.frame) == true){
                        imageI.layer.borderColor = UIColor.greenColor().CGColor;
                        imageI.layer.cornerRadius = 8.0
                        imageI.layer.borderWidth = 3.5;
                        //self.imageInfSelecc = imageI;
                        //self.imageSupSelecc = imagenSeleccionada;
                        println("imagen seleccionada: \(imagenSeleccionada.tag)")
                       if(imagenSeleccionada.tag == imageI.tag){
                             // imageI.image.
                            self.imageInfSelecc = imageI
                            self.imageSupSelecc = imagenSeleccionada;
                        
                           // println("PERFECTO!")
                       }
                        
                        //imagenNUno.
                    }else if (CGRectIntersectsRect(imagenSeleccionada.frame, imageI.frame) == false){
                        imageI.layer.borderColor = UIColor.whiteColor().CGColor;
                      
                    }
                }
           // }
        }
        
        
        if recognizer.state == UIGestureRecognizerState.Ended {
            
            if(self.imageSupSelecc != nil){
                switch self.imageSupSelecc.tag{
                case 0:
                    if(CGRectIntersectsRect(self.imageSupSelecc.frame, self.imageInfSelecc.frame) == true){
                        for var i = 0; i < self.secuencia.count ; i++ {
                            if secuencia[i] == "0" {
                                let imagenURL:NSURL = NSURL(string: self.urlImagenes[i].stringByReplacingOccurrencesOfString(" ", withString: "%20"))!;
                                imagenNUno.hnk_setImageFromURL(imagenURL)
                                 self.imageSupSelecc.hidden = true
                                self.ticketUno.hidden = false
                            }else{
                                recognizer.view!.center = puntoInicialSup
                               // recognizer.view!.center = puntoInicialImagen[0]
                                //recognizer.setTranslation(CGPointZero, inView: self.view)

                            }
                        }
                    }else{
                        recognizer.view!.center = puntoInicialSup
                        recognizer.setTranslation(CGPointZero, inView: self.view)
                        self.imageInfSelecc.layer.borderColor = UIColor.whiteColor().CGColor;
                    }
                case 1:
                    if(CGRectIntersectsRect(self.imageSupSelecc.frame, self.imageInfSelecc.frame) == true){
                    for var i = 0; i < self.secuencia.count ; i++ {
                        if secuencia[i] == "1" {
                            let imagenURL:NSURL = NSURL(string: self.urlImagenes[i].stringByReplacingOccurrencesOfString(" ", withString: "%20"))!;
                            imagenNDos.hnk_setImageFromURL(imagenURL)
                            self.imageSupSelecc.hidden = true
                            self.ticketDos.hidden = false
                        }else{
                            recognizer.view!.center = puntoInicialSup
                           // recognizer.view!.center = puntoInicialImagen[1]
                           // recognizer.setTranslation(CGPointZero, inView: self.view)
                            // self.imageInfSelecc.layer.borderColor = UIColor.whiteColor().CGColor;
                        }
                    }
                    }else{
                       recognizer.view!.center = puntoInicialSup
                        recognizer.setTranslation(CGPointZero, inView: self.view)
                        self.imageInfSelecc.layer.borderColor = UIColor.whiteColor().CGColor;
                    }
                case 2:
                    if(CGRectIntersectsRect(self.imageSupSelecc.frame, self.imageInfSelecc.frame) == true){
                    for var i = 0; i < self.secuencia.count ; i++ {
                        if secuencia[i] == "2" {
                            let imagenURL:NSURL = NSURL(string: self.urlImagenes[i].stringByReplacingOccurrencesOfString(" ", withString: "%20"))!;
                            imagenNTres.hnk_setImageFromURL(imagenURL)
                             self.imageSupSelecc.hidden = true
                            self.ticketTres.hidden = false
                        }
                        else{
                            recognizer.view!.center = puntoInicialSup
                           // recognizer.view!.center = puntoInicialImagen[2]
                           // recognizer.setTranslation(CGPointZero, inView: self.view)

                        }
                    }
                    }else{
                        recognizer.view!.center = puntoInicialSup
                        recognizer.setTranslation(CGPointZero, inView: self.view)
                        self.imageInfSelecc.layer.borderColor = UIColor.whiteColor().CGColor;
                    }
                case 3:
                    if(CGRectIntersectsRect(self.imageSupSelecc.frame, self.imageInfSelecc.frame) == true){
                    for var i = 0; i < self.secuencia.count ; i++ {
                        if secuencia[i] == "3" {
                            let imagenURL:NSURL = NSURL(string: self.urlImagenes[i].stringByReplacingOccurrencesOfString(" ", withString: "%20"))!;
                            imagenNCuatro.hnk_setImageFromURL(imagenURL)
                             self.imageSupSelecc.hidden = true
                            self.ticketCuatro.hidden = false
                        }else{
                            recognizer.view!.center = puntoInicialSup
                           // recognizer.view!.center = puntoInicialImagen[3]
                           // recognizer.setTranslation(CGPointZero, inView: self.view)

                        }
                    }
                    }else{
                        recognizer.view!.center = puntoInicialSup
                        recognizer.setTranslation(CGPointZero, inView: self.view)
                        self.imageInfSelecc.layer.borderColor = UIColor.whiteColor().CGColor;
                    }
                default:
                    println("")
                }
            }
            
            // 1
            let velocity = recognizer.velocityInView(self.view)
            let magnitude = sqrt((velocity.x * velocity.x) + (velocity.y * velocity.y))
            let slideMultiplier = magnitude / 200
            println("magnitude: \(magnitude), slideMultiplier: \(slideMultiplier)")
            
            // 2
            let slideFactor = 0.1 * slideMultiplier     //Increase for more of a slide
            // 3
            var finalPoint = CGPoint(x:recognizer.view!.center.x /*+ (velocity.x * slideFactor)*/,y:recognizer.view!.center.y /*+ (velocity.y * slideFactor)*/)
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
