//
//  SeleccionViewController.swift
//  Creylp
//
//  Created by Marco Baeza on 04-04-15.
//  Copyright (c) 2015 Marco Baeza. All rights reserved.
//

import UIKit

class SeleccionViewController: UIViewController {
    
    
    @IBOutlet weak var titulo: UILabel!
    
    @IBOutlet weak var imagenPrincipal: UIImageView!
    
    @IBOutlet weak var imagenPrimera: UIImageView!
    @IBOutlet weak var imagenSegunda: UIImageView!
    @IBOutlet weak var imagenTercera: UIImageView!
    @IBOutlet weak var imagenCuarta: UIImageView!
    
    @IBOutlet weak var ticketUno: UIImageView!
    @IBOutlet weak var ticketCuatro: UIImageView!
    @IBOutlet weak var ticketTres: UIImageView!
    @IBOutlet weak var ticketDos: UIImageView!

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
        
        let tapGestureUno = UITapGestureRecognizer(target: self, action: "tapGestureUno:")
        let tapGestureDos = UITapGestureRecognizer(target: self, action: "tapGestureDos:")
        let tapGestureTres = UITapGestureRecognizer(target: self, action: "tapGestureTres:")
        let tapGestureCuatro = UITapGestureRecognizer(target: self, action: "tapGestureCuatro:")

        var post:NSString = ""
        
        puntoInicialImagen.append(imagenPrimera.center);
        puntoInicialImagen.append(imagenSegunda.center);
        puntoInicialImagen.append(imagenTercera.center);
        puntoInicialImagen.append(imagenCuarta.center);
        
        //imagenNUno.bringSubviewToFront(imagenPrimera)
        
        /*imagenNUno.hidden = true
        imagenNDos.hidden = true
        imagenNTres.hidden = true
        imagenNCuatro.hidden = true
        
        imagenNUno.tag = 0
        imagenNDos.tag = 1
        imagenNTres.tag = 2
        imagenNCuatro.tag = 3
        */
        
        // imagenPrimera.u
        
        NSLog("PostData: %@",post);
        
        var url:NSURL = NSURL(string: "http://localhost:8080/APP_TDT_WEB-war/webresources/ServiciosWeb/ObtenerImagenEjerciciosSeleccion/"+idEjercicio)!
        
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
                        var principal:Bool = ejercicio.valueForKey("principal") as Bool
                        var respuestaCorrecta:Bool = ejercicio.valueForKey("respuestaCorrecta") as Bool
                        var urlImagen:String = ejercicio.valueForKey("urlImagen") as String
                        
                        NSLog("Url de imagen: " + String(i) + urlImagen);
                        urlImagenes.append(urlImagen);
                        //secuencia.append(String(orden));
                        
                        let imagenURL:NSURL = NSURL(string: urlImagenes[i].stringByReplacingOccurrencesOfString(" ", withString: "%20"))!;
                        
                        switch i {
                        case 0:
                            if(principal == true){
                                imagenPrincipal.hnk_setImageFromURL(imagenURL);
                            }
                        case 1:
                            imagenPrimera.hnk_setImageFromURL(imagenURL);
                            imagenPrimera.addGestureRecognizer(tapGestureUno)
                            imagenPrimera.userInteractionEnabled = true
                            if(respuestaCorrecta == true){
                                imagenPrimera.tag = 1
                            }
                        case 2:
                            imagenSegunda.hnk_setImageFromURL(imagenURL);
                            imagenSegunda.addGestureRecognizer(tapGestureDos)
                            imagenSegunda.userInteractionEnabled = true
                            if(respuestaCorrecta == true){
                                imagenSegunda.tag = 1
                            }
                        case 3:
                            imagenTercera.hnk_setImageFromURL(imagenURL);
                            imagenTercera.addGestureRecognizer(tapGestureTres)
                            imagenTercera.userInteractionEnabled = true
                            if(respuestaCorrecta == true){
                                imagenTercera.tag = 1
                            }
                        case 4:
                            imagenCuarta.hnk_setImageFromURL(imagenURL);
                            imagenCuarta.addGestureRecognizer(tapGestureCuatro)
                            imagenCuarta.userInteractionEnabled = true
                            if(respuestaCorrecta == true){
                                imagenCuarta.tag = 1
                            }
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
    
    
    func tapGestureUno(gesture: UIGestureRecognizer) {
        // if the tapped view is a UIImageView then set it to imageview
        if let imageView = gesture.view as? UIImageView {
            
            if(imageView.tag != 0){
                UIView.animateWithDuration(0.6, delay: 0.0, usingSpringWithDamping: 0.2, initialSpringVelocity: 5, options: nil, animations: {
                    imageView.bounds = CGRect(x: imageView.center.x - 20, y: imageView.center.y - 20, width: imageView.bounds.width + 40, height: imageView.bounds.height+40)
                
                imageView.layer.borderColor = UIColor.greenColor().CGColor;
                imageView.layer.cornerRadius = 8.0
                imageView.layer.borderWidth = 4.5;
                self.ticketUno.hidden = false;
                    }, completion: nil)
            
                UIView.animateWithDuration(0.4, delay: 0.2, usingSpringWithDamping: 0.2, initialSpringVelocity: 5, options: nil, animations: {
                
                    imageView.bounds = CGRect(x: imageView.center.x + 20, y: imageView.center.y + 20, width: imageView.bounds.width - 40, height: imageView.bounds.height-40)
                
                //imageView.layer.borderColor = UIColor.clearColor().CGColor;
                //imageView.layer.cornerRadius = 8.0
                //imageView.layer.borderWidth = 4.5;
                
                    }, completion: nil)
                
            }else if(imageView.tag == 0){
                UIView.animateWithDuration(0.6, delay: 0.0, usingSpringWithDamping: 0.2, initialSpringVelocity: 5, options: nil, animations: {
                    imageView.bounds = CGRect(x: imageView.center.x - 20, y: imageView.center.y - 20, width: imageView.bounds.width + 40, height: imageView.bounds.height+40)
                    
                    }, completion: nil)
                
                UIView.animateWithDuration(0.4, delay: 0.2, usingSpringWithDamping: 0.2, initialSpringVelocity: 5, options: nil, animations: {
                    
                    imageView.bounds = CGRect(x: imageView.center.x + 20, y: imageView.center.y + 20, width: imageView.bounds.width - 40, height: imageView.bounds.height-40)
                    
                    }, completion: nil)
            }
            
            
        }
    }
    
    func tapGestureDos(gesture: UIGestureRecognizer) {
        // if the tapped view is a UIImageView then set it to imageview
        if let imageView = gesture.view as? UIImageView {
            
            if(imageView.tag == 1){
                UIView.animateWithDuration(0.6, delay: 0.0, usingSpringWithDamping: 0.2, initialSpringVelocity: 5, options: nil, animations: {
                    imageView.bounds = CGRect(x: imageView.center.x - 20, y: imageView.center.y - 20, width: imageView.bounds.width + 40, height: imageView.bounds.height+40)
                    
                    imageView.layer.borderColor = UIColor.greenColor().CGColor;
                    imageView.layer.cornerRadius = 8.0
                    imageView.layer.borderWidth = 4.5;
                    self.ticketDos.hidden = false;
                    
                    }, completion: nil)
                
                UIView.animateWithDuration(0.4, delay: 0.2, usingSpringWithDamping: 0.2, initialSpringVelocity: 5, options: nil, animations: {
                    
                    imageView.bounds = CGRect(x: imageView.center.x + 20, y: imageView.center.y + 20, width: imageView.bounds.width - 40, height: imageView.bounds.height-40)
                    
                    //imageView.layer.borderColor = UIColor.clearColor().CGColor;
                    //imageView.layer.cornerRadius = 8.0
                    //imageView.layer.borderWidth = 4.5;
                    
                    }, completion: nil)
                
            }else if(imageView.tag == 0){
                UIView.animateWithDuration(0.6, delay: 0.0, usingSpringWithDamping: 0.2, initialSpringVelocity: 5, options: nil, animations: {
                    imageView.bounds = CGRect(x: imageView.center.x - 20, y: imageView.center.y - 20, width: imageView.bounds.width + 40, height: imageView.bounds.height+40)
                    
                    }, completion: nil)
                
                UIView.animateWithDuration(0.4, delay: 0.2, usingSpringWithDamping: 0.2, initialSpringVelocity: 5, options: nil, animations: {
                    
                    imageView.bounds = CGRect(x: imageView.center.x + 20, y: imageView.center.y + 20, width: imageView.bounds.width - 40, height: imageView.bounds.height-40)
                    
                    }, completion: nil)
            }
            
            
        }
    }
    
    func tapGestureTres(gesture: UIGestureRecognizer) {
        // if the tapped view is a UIImageView then set it to imageview
        if let imageView = gesture.view as? UIImageView {
            
            if(imageView.tag == 1){
                UIView.animateWithDuration(0.6, delay: 0.0, usingSpringWithDamping: 0.2, initialSpringVelocity: 5, options: nil, animations: {
                    imageView.bounds = CGRect(x: imageView.center.x - 20, y: imageView.center.y - 20, width: imageView.bounds.width + 40, height: imageView.bounds.height+40)
                    
                    imageView.layer.borderColor = UIColor.greenColor().CGColor;
                    imageView.layer.cornerRadius = 8.0
                    imageView.layer.borderWidth = 4.5;
                    self.ticketTres.hidden = false;
                    
                    }, completion: nil)
                
                UIView.animateWithDuration(0.4, delay: 0.2, usingSpringWithDamping: 0.2, initialSpringVelocity: 5, options: nil, animations: {
                    
                    imageView.bounds = CGRect(x: imageView.center.x + 20, y: imageView.center.y + 20, width: imageView.bounds.width - 40, height: imageView.bounds.height-40)
                    
                    //imageView.layer.borderColor = UIColor.clearColor().CGColor;
                    //imageView.layer.cornerRadius = 8.0
                    //imageView.layer.borderWidth = 4.5;
                    
                    }, completion: nil)
                
            }else if(imageView.tag == 0){
                UIView.animateWithDuration(0.6, delay: 0.0, usingSpringWithDamping: 0.2, initialSpringVelocity: 5, options: nil, animations: {
                    imageView.bounds = CGRect(x: imageView.center.x - 20, y: imageView.center.y - 20, width: imageView.bounds.width + 40, height: imageView.bounds.height+40)
                    
                    }, completion: nil)
                
                UIView.animateWithDuration(0.4, delay: 0.2, usingSpringWithDamping: 0.2, initialSpringVelocity: 5, options: nil, animations: {
                    
                    imageView.bounds = CGRect(x: imageView.center.x + 20, y: imageView.center.y + 20, width: imageView.bounds.width - 40, height: imageView.bounds.height-40)
                    
                    }, completion: nil)
            }
            
            
        }
    }
    
    func tapGestureCuatro(gesture: UIGestureRecognizer) {
        // if the tapped view is a UIImageView then set it to imageview
        if let imageView = gesture.view as? UIImageView {
            
            if(imageView.tag == 1){
                UIView.animateWithDuration(0.6, delay: 0.0, usingSpringWithDamping: 0.2, initialSpringVelocity: 5, options: nil, animations: {
                    imageView.bounds = CGRect(x: imageView.center.x - 20, y: imageView.center.y - 20, width: imageView.bounds.width + 40, height: imageView.bounds.height+40)
                    
                    imageView.layer.borderColor = UIColor.greenColor().CGColor;
                    imageView.layer.cornerRadius = 8.0
                    imageView.layer.borderWidth = 4.5;
                    self.ticketCuatro.hidden = false;
                    
                    }, completion: nil)
                
                UIView.animateWithDuration(0.4, delay: 0.2, usingSpringWithDamping: 0.2, initialSpringVelocity: 5, options: nil, animations: {
                    
                    imageView.bounds = CGRect(x: imageView.center.x + 20, y: imageView.center.y + 20, width: imageView.bounds.width - 40, height: imageView.bounds.height-40)
                    
                    //imageView.layer.borderColor = UIColor.clearColor().CGColor;
                    //imageView.layer.cornerRadius = 8.0
                    //imageView.layer.borderWidth = 4.5;
                    
                    }, completion: nil)
                
            }else if(imageView.tag == 0){
                UIView.animateWithDuration(0.6, delay: 0.0, usingSpringWithDamping: 0.2, initialSpringVelocity: 5, options: nil, animations: {
                    imageView.bounds = CGRect(x: imageView.center.x - 20, y: imageView.center.y - 20, width: imageView.bounds.width + 40, height: imageView.bounds.height+40)
                    
                    }, completion: nil)
                
                UIView.animateWithDuration(0.4, delay: 0.2, usingSpringWithDamping: 0.2, initialSpringVelocity: 5, options: nil, animations: {
                    
                    imageView.bounds = CGRect(x: imageView.center.x + 20, y: imageView.center.y + 20, width: imageView.bounds.width - 40, height: imageView.bounds.height-40)
                    
                    }, completion: nil)
            }
            
            
        }
    }



    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        
    }
    
   /* @IBAction func handlePinch(recognizer : UIPinchGestureRecognizer) {
        recognizer.view!.transform = CGAffineTransformScale(recognizer.view!.transform,
            recognizer.scale, recognizer.scale)
        recognizer.scale = 1
    }*/
    
    
   /* @IBAction func handlePan(recognizer:UIPanGestureRecognizer) {
        //comment for panning
        //uncomment for tickling
        //ç return;
        
        let translation = recognizer.translationInView(self.view)
        recognizer.view!.center = CGPoint(x:recognizer.view!.center.x + translation.x,y:recognizer.view!.center.y + translation.y)
        recognizer.setTranslation(CGPointZero, inView: self.view)
        
        
        if recognizer.state == UIGestureRecognizerState.Began {
            let imagenSeleccionada1 = recognizer.view as UIImageView
            puntoInicialSup = imagenSeleccionada1.center
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
    }*/

}
