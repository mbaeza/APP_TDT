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

class SecuenciaEjercicioViewController: UIViewController {

    @IBOutlet weak var imagenPrimera: UIImageView!
    var datas: [JSON] = []
    var idEjercicio:String!
    var idAlumno:String!
    var idUsuario:String!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        Alamofire.request(.GET, "http://localhost:8080/APP_TDT_WEB-war/webresources/ServiciosWeb/ObtenerEjercicios/Secuencia/2").responseJSON { (request, response, json, error) in
            println(json);
            println(error);
        }
        
     //   NSLog("Response ==> %@", datas.first);
        
       // let urlString = self.datas["images"]["standard_resolution"]["url"]
       // let url = NSURL(string: urlString.stringValue)
       /// self.imagenPrimera.hnk_setImageFromURL(url!)
        
        //NSLog("Url de imagen: " + url);
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()

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
