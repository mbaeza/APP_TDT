//
//  MemorizeViewController.swift
//  Creylp
//
//  Created by Marco Baeza on 11-04-15.
//  Copyright (c) 2015 Marco Baeza. All rights reserved.
//

import UIKit

class MemorizeViewController: UIViewController {

    @IBOutlet weak var titulo: UILabel!

    @IBOutlet weak var backPrimera: UIView!
    @IBOutlet weak var backSegunda: UIView!
    @IBOutlet weak var backTercera: UIView!
    @IBOutlet weak var backCuarta: UIView!
    @IBOutlet weak var backQuinta: UIView!
    @IBOutlet weak var backSexta: UIView!
    @IBOutlet weak var backSeptima: UIView!
    @IBOutlet weak var backOctava: UIView!
    
    var secuencia:[String]=[]
    var imagenesSuperior:[UIImageView]=[]
    var imagenesInferior:[UIImageView]=[]
    var urlImagenes:[String]=[]
    var idEjercicio:String!
    var idAlumno:String!
    var idUsuario:String!
    
    var imageSupSelecc: UIImageView!
    var imageInfSelecc: UIImageView!
    
    var elegidoPrimero:Int = -1;
    var elegidoSegundo:Int = -1;
    var showingBack = true
    
    //var contenedorUno = UIView()
    //var medidaUno:CGRect!
    let redSquareUno = UIView()
    let redSquareDos = UIView()
    let redSquareTres = UIView()
    let redSquareCuatro = UIView()
    let redSquareCinco = UIView()
    let redSquareSeis = UIView()
    let redSquareSiete = UIView()
    let redSquareOcho = UIView()
    
    let imagenPrimera:UIImageView = UIImageView();
    let imagenSegunda:UIImageView = UIImageView();
    let imagenTercera:UIImageView = UIImageView();
    let imagenCuarta:UIImageView = UIImageView();
    let imagenQuinta:UIImageView = UIImageView();
    let imagenSexta:UIImageView = UIImageView();
    let imagenSeptima:UIImageView = UIImageView();
    let imagenOctava:UIImageView = UIImageView();

    
    override func viewDidLoad() {
        super.viewDidLoad()
        //self.medidaUno = CGRect(x: self.imagenPrimera.bounds.origin.x, y: self.imagenPrimera.bounds.origin.y, width: 129, height: 139)
        var post:NSString = ""
        
        NSLog("PostData: %@",post);
        
        var url:NSURL = NSURL(string: "http://localhost:8080/APP_TDT_WEB-war/webresources/ServiciosWeb/ObtenerImagenEjerciciosMemorize/"+idEjercicio)!
        
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
                      //  var principal:Bool = ejercicio.valueForKey("principal") as Bool
                       // var respuestaCorrecta:Bool = ejercicio.valueForKey("respuestaCorrecta") as Bool
                        var urlImagen:String = ejercicio.valueForKey("urlImagen") as String
                        
                        NSLog("Url de imagen: " + String(i) + urlImagen);
                        urlImagenes.append(urlImagen);
                        //secuencia.append(String(orden));
                        
                        let imagenURL:NSURL = NSURL(string: urlImagenes[i].stringByReplacingOccurrencesOfString(" ", withString: "%20"))!;
                        
                        switch i {
                        case 0:
                            
                            //******Imagen Primera
                            
                            imagenPrimera.tag = 0
                            imagenPrimera.frame = CGRect(x: self.backPrimera.bounds.origin.x, y: self.backPrimera.bounds.origin.y, width: 129, height: 139)
                            imagenPrimera.hnk_setImageFromURL(imagenURL);
                            self.redSquareUno.frame = CGRect(x: self.backOctava.bounds.origin.x, y: self.backOctava.bounds.origin.y, width: 129, height: 139)
                            //self.contenedorUno.addSubview(imagenPrimera)
                            
                            let tapGestureUno = UITapGestureRecognizer(target: self, action: Selector("tapGestureUno"))
                            tapGestureUno.numberOfTapsRequired = 1
                            
                            self.redSquareUno.frame = CGRect(x: self.backPrimera.bounds.origin.x, y: self.backPrimera.bounds.origin.y, width: 129, height: 139)
                            self.redSquareUno.addGestureRecognizer(tapGestureUno)
                            self.redSquareUno.backgroundColor = UIColor.redColor()
                            self.backPrimera.addSubview(self.redSquareUno)
                            
                            //*******Imagen Octava
                            
                            imagenOctava.frame = CGRect(x: self.backOctava.bounds.origin.x, y: self.backOctava.bounds.origin.y, width: 129, height: 139)
                            imagenOctava.hnk_setImageFromURL(imagenURL);
                            imagenOctava.tag = 7
                            
                            let tapGestureOcho = UITapGestureRecognizer(target: self, action: Selector("tapGestureOcho"))
                            tapGestureOcho.numberOfTapsRequired = 1
                            
                            self.redSquareOcho.frame = CGRect(x: self.backOctava.bounds.origin.x, y: self.backOctava.bounds.origin.y, width: 129, height: 139)
                            self.redSquareOcho.addGestureRecognizer(tapGestureOcho)
                            self.redSquareOcho.backgroundColor = UIColor.redColor()
                            self.backOctava.addSubview(self.redSquareOcho)

                        case 1:
                            
                            //*********Imagen Segunda
                            
                            imagenSegunda.frame = CGRect(x: self.backSegunda.bounds.origin.x, y: self.backSegunda.bounds.origin.y, width: 129, height: 139)
                            imagenSegunda.hnk_setImageFromURL(imagenURL);
                            imagenSegunda.tag = 1
                            
                            let tapGestureDos = UITapGestureRecognizer(target: self, action: Selector("tapGestureDos"))
                            tapGestureDos.numberOfTapsRequired = 1
                            
                            self.redSquareDos.frame = CGRect(x: self.backSegunda.bounds.origin.x, y: self.backSegunda.bounds.origin.y, width: 129, height: 139)
                            self.redSquareDos.addGestureRecognizer(tapGestureDos)
                            self.redSquareDos.backgroundColor = UIColor.redColor()
                            self.backSegunda.addSubview(self.redSquareDos)
                            
                            
                            //****** Imagen Septima
                            imagenSeptima.frame = CGRect(x: self.backSeptima.bounds.origin.x, y: self.backSeptima.bounds.origin.y, width: 129, height: 139)
                            imagenSeptima.hnk_setImageFromURL(imagenURL);
                            imagenSeptima.tag = 6
                            
                            let tapGestureSiete = UITapGestureRecognizer(target: self, action: Selector("tapGestureSiete"))
                            tapGestureSiete.numberOfTapsRequired = 1
                            
                            self.redSquareSiete.frame = CGRect(x: self.backSeptima.bounds.origin.x, y: self.backSeptima.bounds.origin.y, width: 129, height: 139)
                            self.redSquareSiete.addGestureRecognizer(tapGestureSiete)
                            self.redSquareSiete.backgroundColor = UIColor.redColor()
                            self.backSeptima.addSubview(self.redSquareSiete)
  
                        case 2:
                            
                            //***Imagen Tercera
                            
                            imagenTercera.frame = CGRect(x: self.backTercera.bounds.origin.x, y: self.backTercera.bounds.origin.y, width: 129, height: 139)
                            imagenTercera.hnk_setImageFromURL(imagenURL);
                            imagenTercera.tag = 2
                            
                            let tapGestureTres = UITapGestureRecognizer(target: self, action: Selector("tapGestureTres"))
                            tapGestureTres.numberOfTapsRequired = 1
                            
                            self.redSquareTres.frame = CGRect(x: self.backTercera.bounds.origin.x, y: self.backTercera.bounds.origin.y, width: 129, height: 139)
                            self.redSquareTres.addGestureRecognizer(tapGestureTres)
                            self.redSquareTres.backgroundColor = UIColor.redColor()
                            self.backTercera.addSubview(self.redSquareTres)
                            
                            
                            //**** Imagen Quinta
                            
                            imagenQuinta.frame = CGRect(x: self.backQuinta.bounds.origin.x, y: self.backQuinta.bounds.origin.y, width: 129, height: 139)
                            imagenQuinta.hnk_setImageFromURL(imagenURL);
                            imagenQuinta.tag = 4
                            
                            let tapGestureCinco = UITapGestureRecognizer(target: self, action: Selector("tapGestureCinco"))
                            tapGestureCinco.numberOfTapsRequired = 1
                            
                            self.redSquareCinco.frame = CGRect(x: self.backQuinta.bounds.origin.x, y: self.backQuinta.bounds.origin.y, width: 129, height: 139)
                            self.redSquareCinco.addGestureRecognizer(tapGestureCinco)
                            self.redSquareCinco.backgroundColor = UIColor.redColor()
                            self.backQuinta.addSubview(self.redSquareCinco)
                            
                        case 3:
                            
                            //***Imagen Cuarta
                            
                            imagenCuarta.frame = CGRect(x: self.backCuarta.bounds.origin.x, y: self.backCuarta.bounds.origin.y, width: 129, height: 139)
                            imagenCuarta.hnk_setImageFromURL(imagenURL);
                            imagenCuarta.tag = 3
                            
                            let tapGestureCuatro = UITapGestureRecognizer(target: self, action: Selector("tapGestureCuatro"))
                            tapGestureCuatro.numberOfTapsRequired = 1
                            
                            self.redSquareCuatro.frame = CGRect(x: self.backCuarta.bounds.origin.x, y: self.backCuarta.bounds.origin.y, width: 129, height: 139)
                            self.redSquareCuatro.addGestureRecognizer(tapGestureCuatro)
                            self.redSquareCuatro.backgroundColor = UIColor.redColor()
                            self.backCuarta.addSubview(self.redSquareCuatro)
                            
                            
                            //***Imagen Sexta
                            
                            imagenSexta.frame = CGRect(x: self.backSexta.bounds.origin.x, y: self.backSexta.bounds.origin.y, width: 129, height: 139)
                            imagenSexta.hnk_setImageFromURL(imagenURL);
                            imagenSexta.tag = 5
                            
                            let tapGestureSeis = UITapGestureRecognizer(target: self, action: Selector("tapGestureSeis"))
                            tapGestureSeis.numberOfTapsRequired = 1
                            
                            self.redSquareSeis.frame = CGRect(x: self.backSexta.bounds.origin.x, y: self.backSexta.bounds.origin.y, width: 129, height: 139)
                            self.redSquareSeis.addGestureRecognizer(tapGestureSeis)
                            self.redSquareSeis.backgroundColor = UIColor.redColor()
                            self.backSexta.addSubview(self.redSquareSeis)
                        default:
                            println("asd");
                            //imagenPrimera.hnk_setImageFromURL(imagenURL);
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
        
        
    }
    
    func tapGestureUno() {

        if(self.elegidoPrimero == -1){
            self.elegidoPrimero = self.imagenPrimera.tag
            
                UIView.transitionFromView(self.redSquareUno, toView: self.imagenPrimera, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)

        }else if(self.elegidoSegundo == -1){
            if(self.elegidoPrimero != 7){
               
                switch(self.elegidoPrimero){
                    case 1:
                        UIView.transitionFromView(self.redSquareUno, toView: self.imagenPrimera, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: {(Bool finished)->() in
                            UIView.animateWithDuration(0.6, delay: 0.1, usingSpringWithDamping: 0.2, initialSpringVelocity: 1, options: nil, animations: {
                                UIView.transitionFromView(self.imagenPrimera, toView: self.redSquareUno, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
                                UIView.transitionFromView(self.imagenSegunda, toView: self.redSquareDos, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
                                }, completion: nil)
                        })
                    
                    case 2:
                        UIView.transitionFromView(self.redSquareUno, toView: self.imagenPrimera, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: {(Bool finished)->() in
                            UIView.animateWithDuration(0.6, delay: 0.1, usingSpringWithDamping: 0.2, initialSpringVelocity: 1, options: nil, animations: {
                                UIView.transitionFromView(self.imagenPrimera, toView: self.redSquareUno, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
                                 UIView.transitionFromView(self.imagenTercera, toView: self.redSquareTres, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
                                }, completion: nil)
                        })
                    
                    case 3:
                        UIView.transitionFromView(self.redSquareUno, toView: self.imagenPrimera, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: {(Bool finished)->() in
                            UIView.animateWithDuration(0.6, delay: 0.1, usingSpringWithDamping: 0.2, initialSpringVelocity: 1, options: nil, animations: {
                                UIView.transitionFromView(self.imagenPrimera, toView: self.redSquareUno, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
                                UIView.transitionFromView(self.imagenCuarta, toView: self.redSquareCuatro, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
                                }, completion: nil)
                        })
                    
                    case 4:
                        UIView.transitionFromView(self.redSquareUno, toView: self.imagenPrimera, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: {(Bool finished)->() in
                            UIView.animateWithDuration(0.6, delay: 0.1, usingSpringWithDamping: 0.2, initialSpringVelocity: 1, options: nil, animations: {
                                UIView.transitionFromView(self.imagenPrimera, toView: self.redSquareUno, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
                                UIView.transitionFromView(self.imagenQuinta, toView: self.redSquareCinco, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
                                }, completion: nil)
                        })
                    
                    case 5:
                        UIView.transitionFromView(self.redSquareUno, toView: self.imagenPrimera, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: {(Bool finished)->() in
                            UIView.animateWithDuration(0.6, delay: 0.1, usingSpringWithDamping: 0.2, initialSpringVelocity: 1, options: nil, animations: {
                                UIView.transitionFromView(self.imagenPrimera, toView: self.redSquareUno, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
                                UIView.transitionFromView(self.imagenSexta, toView: self.redSquareSeis, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
                                }, completion: nil)
                        })
                    
                    case 6:
                        UIView.transitionFromView(self.redSquareUno, toView: self.imagenPrimera, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: {(Bool finished)->() in
                            UIView.animateWithDuration(0.6, delay: 0.1, usingSpringWithDamping: 0.2, initialSpringVelocity: 1, options: nil, animations: {
                                UIView.transitionFromView(self.imagenPrimera, toView: self.redSquareUno, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
                                UIView.transitionFromView(self.imagenSeptima, toView: self.redSquareSiete, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
                                }, completion: nil)
                        })
                    
                    default:
                        println()
                    }

            }else{
                self.imagenPrimera.layer.borderColor = UIColor.greenColor().CGColor;
                self.imagenPrimera.layer.cornerRadius = 8.0
                self.imagenPrimera.layer.borderWidth = 4.5;
                
                self.imagenOctava.layer.borderColor = UIColor.greenColor().CGColor;
                self.imagenOctava.layer.cornerRadius = 8.0
                self.imagenOctava.layer.borderWidth = 4.5;
                
            }
            
             self.elegidoPrimero = -1
        }
        
        
        
    }
    
    func tapGestureDos() {
        
        if(self.elegidoPrimero == -1){
            self.elegidoPrimero = self.imagenSegunda.tag
            UIView.transitionFromView(self.redSquareDos, toView: self.imagenSegunda, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
        }else if(self.elegidoSegundo == -1){
            if(self.elegidoPrimero != 6){
                
                
                switch(self.elegidoPrimero){
                case 0:
                    UIView.transitionFromView(self.redSquareDos, toView: self.imagenSegunda, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: {(Bool finished)->() in
                        UIView.animateWithDuration(0.6, delay: 0.1, usingSpringWithDamping: 0.2, initialSpringVelocity: 1, options: nil, animations: {
                            UIView.transitionFromView(self.imagenSegunda, toView: self.redSquareDos, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromRight, completion: nil)
                            UIView.transitionFromView(self.imagenPrimera, toView: self.redSquareUno, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
                            }, completion: nil)
                    })
                    
                case 2:
                    UIView.transitionFromView(self.redSquareDos, toView: self.imagenSegunda, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: {(Bool finished)->() in
                        UIView.animateWithDuration(0.6, delay: 0.1, usingSpringWithDamping: 0.2, initialSpringVelocity: 1, options: nil, animations: {
                            UIView.transitionFromView(self.imagenSegunda, toView: self.redSquareDos, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromRight, completion: nil)
                            UIView.transitionFromView(self.imagenTercera, toView: self.redSquareTres, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
                            }, completion: nil)
                    })
                    
                case 3:
                    UIView.transitionFromView(self.redSquareDos, toView: self.imagenSegunda, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: {(Bool finished)->() in
                        UIView.animateWithDuration(0.6, delay: 0.1, usingSpringWithDamping: 0.2, initialSpringVelocity: 1, options: nil, animations: {
                            UIView.transitionFromView(self.imagenSegunda, toView: self.redSquareDos, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromRight, completion: nil)
                            UIView.transitionFromView(self.imagenCuarta, toView: self.redSquareCuatro, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
                            }, completion: nil)
                    })
                    
                case 4:
                    UIView.transitionFromView(self.redSquareDos, toView: self.imagenSegunda, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: {(Bool finished)->() in
                        UIView.animateWithDuration(0.6, delay: 0.1, usingSpringWithDamping: 0.2, initialSpringVelocity: 1, options: nil, animations: {
                            UIView.transitionFromView(self.imagenSegunda, toView: self.redSquareDos, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromRight, completion: nil)
                            UIView.transitionFromView(self.imagenQuinta, toView: self.redSquareCinco, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
                            }, completion: nil)
                    })
                    
                case 5:
                   UIView.transitionFromView(self.redSquareDos, toView: self.imagenSegunda, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: {(Bool finished)->() in
                        UIView.animateWithDuration(0.6, delay: 0.1, usingSpringWithDamping: 0.2, initialSpringVelocity: 1, options: nil, animations: {
                            UIView.transitionFromView(self.imagenSegunda, toView: self.redSquareDos, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromRight, completion: nil)
                            UIView.transitionFromView(self.imagenSexta, toView: self.redSquareSeis, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
                            }, completion: nil)
                    })
                    
                case 7:
                    UIView.transitionFromView(self.redSquareDos, toView: self.imagenSegunda, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: {(Bool finished)->() in
                        UIView.animateWithDuration(0.6, delay: 0.1, usingSpringWithDamping: 0.2, initialSpringVelocity: 1, options: nil, animations: {
                            UIView.transitionFromView(self.imagenSegunda, toView: self.redSquareDos, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromRight, completion: nil)
                            UIView.transitionFromView(self.imagenOctava, toView: self.redSquareOcho, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
                            }, completion: nil)
                    })
                    
                default:
                    UIView.transitionFromView(self.imagenPrimera, toView: self.redSquareUno, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
                }
            }else{
                self.imagenSegunda.layer.borderColor = UIColor.greenColor().CGColor;
                self.imagenSegunda.layer.cornerRadius = 8.0
                self.imagenSegunda.layer.borderWidth = 4.5;
                
                self.imagenSeptima.layer.borderColor = UIColor.greenColor().CGColor;
                self.imagenSeptima.layer.cornerRadius = 8.0
                self.imagenSeptima.layer.borderWidth = 4.5;
                
            }
            
            self.elegidoPrimero = -1
        }

    }
    
    func tapGestureTres() {
        
        if(self.elegidoPrimero == -1){
            self.elegidoPrimero = self.imagenTercera.tag
            UIView.transitionFromView(self.redSquareTres, toView: self.imagenTercera, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
        }else if(self.elegidoSegundo == -1){
            if(self.elegidoPrimero != 4){
                
                
                switch(self.elegidoPrimero){
                case 0:
                    UIView.transitionFromView(self.redSquareTres, toView: self.imagenTercera, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: {(Bool finished)->() in
                        UIView.animateWithDuration(0.6, delay: 0.1, usingSpringWithDamping: 0.2, initialSpringVelocity: 1, options: nil, animations: {
                            UIView.transitionFromView(self.imagenTercera, toView: self.redSquareTres, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
                            UIView.transitionFromView(self.imagenPrimera, toView: self.redSquareUno, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
                            }, completion: nil)
                    })
                    
                case 1:
                    UIView.transitionFromView(self.redSquareTres, toView: self.imagenTercera, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: {(Bool finished)->() in
                        UIView.animateWithDuration(0.6, delay: 0.1, usingSpringWithDamping: 0.2, initialSpringVelocity: 1, options: nil, animations: {
                            UIView.transitionFromView(self.imagenTercera, toView: self.redSquareTres, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
                            UIView.transitionFromView(self.imagenSegunda, toView: self.redSquareDos, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
                            }, completion: nil)
                    })
                    
                case 3:
                    UIView.transitionFromView(self.redSquareTres, toView: self.imagenTercera, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: {(Bool finished)->() in
                        UIView.animateWithDuration(0.6, delay: 0.1, usingSpringWithDamping: 0.2, initialSpringVelocity: 1, options: nil, animations: {
                            UIView.transitionFromView(self.imagenTercera, toView: self.redSquareTres, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
                            UIView.transitionFromView(self.imagenCuarta, toView: self.redSquareCuatro, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
                            }, completion: nil)
                    })
                    
                case 5:
                    UIView.transitionFromView(self.redSquareTres, toView: self.imagenTercera, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: {(Bool finished)->() in
                        UIView.animateWithDuration(0.6, delay: 0.1, usingSpringWithDamping: 0.2, initialSpringVelocity: 1, options: nil, animations: {
                            UIView.transitionFromView(self.imagenTercera, toView: self.redSquareTres, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
                            UIView.transitionFromView(self.imagenSexta, toView: self.redSquareSeis, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
                            }, completion: nil)
                    })
                    
                case 6:
                    UIView.transitionFromView(self.redSquareTres, toView: self.imagenTercera, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: {(Bool finished)->() in
                        UIView.animateWithDuration(0.6, delay: 0.1, usingSpringWithDamping: 0.2, initialSpringVelocity: 1, options: nil, animations: {
                            UIView.transitionFromView(self.imagenTercera, toView: self.redSquareTres, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
                            UIView.transitionFromView(self.imagenSeptima, toView: self.redSquareSiete, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
                            }, completion: nil)
                    })
                    
                case 7:
                    UIView.transitionFromView(self.redSquareTres, toView: self.imagenTercera, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: {(Bool finished)->() in
                        UIView.animateWithDuration(0.6, delay: 0.1, usingSpringWithDamping: 0.2, initialSpringVelocity: 1, options: nil, animations: {
                            UIView.transitionFromView(self.imagenTercera, toView: self.redSquareTres, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
                            UIView.transitionFromView(self.imagenOctava, toView: self.redSquareOcho, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
                            }, completion: nil)
                    })
                    
                default:
                    UIView.transitionFromView(self.imagenPrimera, toView: self.redSquareUno, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
                }
            }else{
                self.imagenTercera.layer.borderColor = UIColor.greenColor().CGColor;
                self.imagenTercera.layer.cornerRadius = 8.0
                self.imagenTercera.layer.borderWidth = 4.5;
                
                self.imagenQuinta.layer.borderColor = UIColor.greenColor().CGColor;
                self.imagenQuinta.layer.cornerRadius = 8.0
                self.imagenQuinta.layer.borderWidth = 4.5;
                self.elegidoPrimero = -1
            }
             self.elegidoPrimero = -1

    }
    }
    
    func tapGestureCuatro() {
        
        
        
        if(self.elegidoPrimero == -1){
            self.elegidoPrimero = self.imagenCuarta.tag
            UIView.transitionFromView(self.redSquareCuatro, toView: self.imagenCuarta, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
        }else if(self.elegidoSegundo == -1){
            if(self.elegidoPrimero != 5){
                
                UIView.transitionFromView(self.imagenCuarta, toView: self.redSquareCuatro, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromRight, completion: nil)
                
                switch(self.elegidoPrimero){
                case 0:
                    UIView.transitionFromView(self.redSquareCuatro, toView: self.imagenCuarta, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: {(Bool finished)->() in
                        UIView.animateWithDuration(0.6, delay: 0.1, usingSpringWithDamping: 0.2, initialSpringVelocity: 1, options: nil, animations: {
                            UIView.transitionFromView(self.imagenCuarta, toView: self.redSquareCuatro, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromRight, completion: nil)
                            UIView.transitionFromView(self.imagenPrimera, toView: self.redSquareUno, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
                            }, completion: nil)
                    })

                case 1:
                    UIView.transitionFromView(self.redSquareCuatro, toView: self.imagenCuarta, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: {(Bool finished)->() in
                        UIView.animateWithDuration(0.6, delay: 0.1, usingSpringWithDamping: 0.2, initialSpringVelocity: 1, options: nil, animations: {
                            UIView.transitionFromView(self.imagenCuarta, toView: self.redSquareCuatro, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromRight, completion: nil)
                            UIView.transitionFromView(self.imagenSegunda, toView: self.redSquareDos, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
                            }, completion: nil)
                    })
                    
                case 2:
                    UIView.transitionFromView(self.redSquareCuatro, toView: self.imagenCuarta, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: {(Bool finished)->() in
                        UIView.animateWithDuration(0.6, delay: 0.1, usingSpringWithDamping: 0.2, initialSpringVelocity: 1, options: nil, animations: {
                            UIView.transitionFromView(self.imagenCuarta, toView: self.redSquareCuatro, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromRight, completion: nil)
                            UIView.transitionFromView(self.imagenTercera, toView: self.redSquareTres, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
                            }, completion: nil)
                    })
                    
                case 4:
                    UIView.transitionFromView(self.redSquareCuatro, toView: self.imagenCuarta, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: {(Bool finished)->() in
                        UIView.animateWithDuration(0.6, delay: 0.1, usingSpringWithDamping: 0.2, initialSpringVelocity: 1, options: nil, animations: {
                            UIView.transitionFromView(self.imagenCuarta, toView: self.redSquareCuatro, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromRight, completion: nil)
                            UIView.transitionFromView(self.imagenQuinta, toView: self.redSquareCinco, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
                            }, completion: nil)
                    })
                    
                case 6:
                    UIView.transitionFromView(self.redSquareCuatro, toView: self.imagenCuarta, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: {(Bool finished)->() in
                        UIView.animateWithDuration(0.6, delay: 0.1, usingSpringWithDamping: 0.2, initialSpringVelocity: 1, options: nil, animations: {
                            UIView.transitionFromView(self.imagenCuarta, toView: self.redSquareCuatro, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromRight, completion: nil)
                            UIView.transitionFromView(self.imagenSeptima, toView: self.redSquareSiete, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
                            }, completion: nil)
                    })
                    
                case 7:
                    UIView.transitionFromView(self.redSquareCuatro, toView: self.imagenCuarta, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: {(Bool finished)->() in
                        UIView.animateWithDuration(0.6, delay: 0.1, usingSpringWithDamping: 0.2, initialSpringVelocity: 1, options: nil, animations: {
                            UIView.transitionFromView(self.imagenCuarta, toView: self.redSquareCuatro, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromRight, completion: nil)
                             UIView.transitionFromView(self.imagenOctava, toView: self.redSquareOcho, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
                            }, completion: nil)
                    })
                   
                default:
                    UIView.transitionFromView(self.imagenPrimera, toView: self.redSquareUno, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
                }
            }else{
                self.imagenCuarta.layer.borderColor = UIColor.greenColor().CGColor;
                self.imagenCuarta.layer.cornerRadius = 8.0
                self.imagenCuarta.layer.borderWidth = 4.5;
                
                self.imagenSexta.layer.borderColor = UIColor.greenColor().CGColor;
                self.imagenSexta.layer.cornerRadius = 8.0
                self.imagenSexta.layer.borderWidth = 4.5;
                
            }
            
            self.elegidoPrimero = -1
        }

    }
    
    func tapGestureCinco() {
        UIView.transitionFromView(self.redSquareCinco, toView: self.imagenQuinta, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
        
        
        if(self.elegidoPrimero == -1){
            self.elegidoPrimero = self.imagenQuinta.tag
        }else if(self.elegidoSegundo == -1){
            if(self.elegidoPrimero != 2){
                UIView.transitionFromView(self.imagenQuinta, toView: self.redSquareCinco, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
                
                switch(self.elegidoPrimero){
                case 0:
                    UIView.transitionFromView(self.imagenPrimera, toView: self.redSquareUno, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
                case 1:
                    UIView.transitionFromView(self.imagenSegunda, toView: self.redSquareDos, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
                case 3:
                    UIView.transitionFromView(self.imagenCuarta, toView: self.redSquareCuatro, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
                case 5:
                    UIView.transitionFromView(self.imagenSexta, toView: self.redSquareSeis, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
                case 6:
                    UIView.transitionFromView(self.imagenSeptima, toView: self.redSquareSiete, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
                case 7:
                    UIView.transitionFromView(self.imagenOctava, toView: self.redSquareOcho, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
                default:
                    UIView.transitionFromView(self.imagenPrimera, toView: self.redSquareUno, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
                }
                
            }else{
                self.imagenTercera.layer.borderColor = UIColor.greenColor().CGColor;
                self.imagenTercera.layer.cornerRadius = 8.0
                self.imagenTercera.layer.borderWidth = 4.5;
                
                self.imagenQuinta.layer.borderColor = UIColor.greenColor().CGColor;
                self.imagenQuinta.layer.cornerRadius = 8.0
                self.imagenQuinta.layer.borderWidth = 4.5;
                
            }
            self.elegidoPrimero = -1


    }
        }
    
    func tapGestureSeis() {
        
        UIView.transitionFromView(self.redSquareSeis, toView: self.imagenSexta, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
        
        if(self.elegidoPrimero == -1){
            self.elegidoPrimero = self.imagenSexta.tag
        }else if(self.elegidoSegundo == -1){
            if(self.elegidoPrimero != 3){
                
                UIView.transitionFromView(self.imagenSexta, toView: self.redSquareSeis, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromRight, completion: nil)
                
                switch(self.elegidoPrimero){
                case 0:
                    UIView.transitionFromView(self.imagenPrimera, toView: self.redSquareUno, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
                case 1:
                    UIView.transitionFromView(self.imagenSegunda, toView: self.redSquareDos, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
                case 2:
                    UIView.transitionFromView(self.imagenTercera, toView: self.redSquareTres, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
                case 4:
                    UIView.transitionFromView(self.imagenQuinta, toView: self.redSquareCinco, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
                case 6:
                    UIView.transitionFromView(self.imagenSeptima, toView: self.redSquareSiete, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
                case 7:
                    UIView.transitionFromView(self.imagenOctava, toView: self.redSquareOcho, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
                default:
                    UIView.transitionFromView(self.imagenPrimera, toView: self.redSquareUno, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
                }
            }else{
                self.imagenSexta.layer.borderColor = UIColor.greenColor().CGColor;
                self.imagenSexta.layer.cornerRadius = 8.0
                self.imagenSexta.layer.borderWidth = 4.5;
                
                self.imagenCuarta.layer.borderColor = UIColor.greenColor().CGColor;
                self.imagenCuarta.layer.cornerRadius = 8.0
                self.imagenCuarta.layer.borderWidth = 4.5;
                
            }
            
            self.elegidoPrimero = -1
        }
    }
    
    func tapGestureSiete() {
        
        UIView.transitionFromView(self.redSquareSiete, toView: self.imagenSeptima, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
        
        if(self.elegidoPrimero == -1){
            self.elegidoPrimero = self.imagenSeptima.tag
        }else if(self.elegidoSegundo == -1){
            if(self.elegidoPrimero != 1){
                
                UIView.transitionFromView(self.imagenSeptima, toView: self.redSquareSiete, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromRight, completion: nil)
                
                switch(self.elegidoPrimero){
                case 0:
                    UIView.transitionFromView(self.imagenPrimera, toView: self.redSquareUno, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
                case 2:
                    UIView.transitionFromView(self.imagenTercera, toView: self.redSquareTres, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
                case 3:
                    UIView.transitionFromView(self.imagenCuarta, toView: self.redSquareCuatro, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
                case 4:
                    UIView.transitionFromView(self.imagenQuinta, toView: self.redSquareCinco, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
                case 5:
                    UIView.transitionFromView(self.imagenSexta, toView: self.redSquareSeis, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
                case 7:
                    UIView.transitionFromView(self.imagenOctava, toView: self.redSquareOcho, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
                default:
                    UIView.transitionFromView(self.imagenPrimera, toView: self.redSquareUno, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
                }
            }else{
                self.imagenSeptima.layer.borderColor = UIColor.greenColor().CGColor;
                self.imagenSeptima.layer.cornerRadius = 8.0
                self.imagenSeptima.layer.borderWidth = 4.5;
                
                self.imagenSegunda.layer.borderColor = UIColor.greenColor().CGColor;
                self.imagenSegunda.layer.cornerRadius = 8.0
                self.imagenSegunda.layer.borderWidth = 4.5;
                
            }
            
            self.elegidoPrimero = -1
        }

    }
    
    func tapGestureOcho() {
        UIView.transitionFromView(self.redSquareOcho, toView: self.imagenOctava, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
        
        if(self.elegidoPrimero == -1){
            self.elegidoPrimero = self.imagenOctava.tag
        }else if(self.elegidoSegundo == -1){
            if(self.elegidoPrimero != 0){
                UIView.transitionFromView(self.imagenOctava, toView: self.redSquareOcho, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
                
                switch(self.elegidoPrimero){
                case 1:
                    UIView.transitionFromView(self.imagenSegunda, toView: self.redSquareDos, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
                case 2:
                    UIView.transitionFromView(self.imagenTercera, toView: self.redSquareTres, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
                case 3:
                    UIView.transitionFromView(self.imagenCuarta, toView: self.redSquareCuatro, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
                case 4:
                    UIView.transitionFromView(self.imagenQuinta, toView: self.redSquareCinco, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
                case 5:
                    UIView.transitionFromView(self.imagenSexta, toView: self.redSquareSeis, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
                case 6:
                    UIView.transitionFromView(self.imagenSeptima, toView: self.redSquareSiete, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
                default:
                    UIView.transitionFromView(self.imagenPrimera, toView: self.redSquareUno, duration: 1.0, options: UIViewAnimationOptions.TransitionFlipFromLeft, completion: nil)
                }
            }else{
                self.imagenPrimera.layer.borderColor = UIColor.greenColor().CGColor;
                self.imagenPrimera.layer.cornerRadius = 8.0
                self.imagenPrimera.layer.borderWidth = 4.5;
                
                self.imagenOctava.layer.borderColor = UIColor.greenColor().CGColor;
                self.imagenOctava.layer.cornerRadius = 8.0
                self.imagenOctava.layer.borderWidth = 4.5;
                self.elegidoPrimero = -1

            }
            self.elegidoPrimero = -1

            
    }
    }
    
    /*func tapGestureDos(gesture: UIGestureRecognizer) {
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
    }*/
    
    
    
    
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
