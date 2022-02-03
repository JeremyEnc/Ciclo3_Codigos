/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Controlador.EstructurasDinamicas.Lista;
import Controlador.Grafos.Adyacencia;
import Controlador.Grafos.GrafoEtiquetadoD;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.layout.mxFastOrganicLayout;
import com.mxgraph.layout.mxGraphLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.swing.util.mxMorphing;
import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource;
import com.mxgraph.view.mxGraph;
import java.awt.Dimension;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


/**
 * FXML Controller class
 *
 * @author jere_
 */
public class ControladorVentanaGrafo implements Initializable {

    @FXML
    private SwingNode swGrafo;
    
    ControladorParadaBus ctrlParadaBus;
    String camino;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
       
    }    
    
    public void asignarControlador(ControladorParadaBus ctrlParadaBus, String caminos)
    {
        this.ctrlParadaBus = ctrlParadaBus;
        this.camino = caminos;
        cargarGrafo();
    }
    
    private void cargarGrafo()
    {
        mxGraph grafo = new mxGraph();
        mxGraphComponent compGrafo = new mxGraphComponent(grafo);
        compGrafo.setSize(new Dimension(400,400));
        
        Object parent = grafo.getDefaultParent();
        grafo.getModel().beginUpdate();
        
        
        try 
        {
            GrafoEtiquetadoD ged = ctrlParadaBus.getGrafo();
            Object start[] = new Object[ged.numVertices()];

            for (int i = 0; i < ged.numVertices(); i++)
            {   
                start[i] = grafo.insertVertex(parent, String.valueOf(i + 1), ged.obtenerEtiqueta(i), 100, 100, 80, 30);
            }
            
            for (int i = 0; i < start.length; i++)
            {
                Lista<Adyacencia> listaAdyacencias = ged.adyacentes(i);
                
                for (int j = 0; j < listaAdyacencias.length(); j++)
                {
                    Adyacencia aux = listaAdyacencias.getByIndex(j);
                    grafo.insertEdge(parent, null, "", start[i], start[aux.getDestino()]);
                }
                
            }
        } 
        catch (Exception e)
        {
            System.out.println(e);
        }
        finally
        {
            grafo.getModel().endUpdate(); 
        }
        
        compGrafo.setDragEnabled(false);
        morphGraph(grafo, compGrafo);
        new mxHierarchicalLayout(grafo).execute(grafo.getDefaultParent());
        swGrafo.setContent(compGrafo);
        
    }
    
    private static void morphGraph(mxGraph grafo, mxGraphComponent compGrafo)
    {
        mxGraphLayout layout = new mxFastOrganicLayout(grafo);
        grafo.getModel().beginUpdate();
        
        try 
        {
            layout.execute(grafo.getDefaultParent());
        }
        catch (Exception e)
        {
            
        }
        finally
        {
            mxMorphing mxm = new mxMorphing(compGrafo, 20,1.5,20);
            mxm.addListener(mxEvent.DONE, new mxEventSource.mxIEventListener()
            {
                @Override
                public void invoke(Object o, mxEventObject eo)
                {
                    grafo.getModel().endUpdate();
                }

            });
            mxm.startAnimation();
        }
    }
    
    
}
