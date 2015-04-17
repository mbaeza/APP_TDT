//
//  MenuInicialViewController.swift
//  Creylp
//
//  Created by Marco Baeza on 01-03-15.
//  Copyright (c) 2015 Marco Baeza. All rights reserved.
//

import UIKit

class MenuInicialViewController: UIViewController {

    var idUsuario:String!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        NSLog("IdUsuario: " + idUsuario);
        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    
    
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        if segue.identifier == "SegueSecuencia"{
            //let nav = segue.destinationViewController as UINavigationController;
            //let vc = nav.viewControllers[0] as EjerciciosViewController;
            let vc = segue.destinationViewController as EjerciciosViewController;
            vc.idUsuario = self.idUsuario;
            vc.tipoEjercicio = "Secuencia";
            //vc.delegate = self;
        }else if segue.identifier == "SegueSemejanza"{
            let vc = segue.destinationViewController as EjerciciosSemejanzaViewController
            vc.idUsuario = self.idUsuario;
            vc.tipoEjercicio = "Semejanza";
            // vc.delegate = self
        }else if segue.identifier == "SegueSeleccion"{
            let vc = segue.destinationViewController as EjerciciosSeleccionViewController
            vc.idUsuario = self.idUsuario;
            vc.tipoEjercicio = "Seleccion";
            // vc.delegate = self
        }else if segue.identifier == "SegueMemorize"{
            let vc = segue.destinationViewController as EjerciciosMemorizeViewController
            vc.idUsuario = self.idUsuario;
            vc.tipoEjercicio = "Memorize";
            // vc.delegate = self
        }



    }
    

}
