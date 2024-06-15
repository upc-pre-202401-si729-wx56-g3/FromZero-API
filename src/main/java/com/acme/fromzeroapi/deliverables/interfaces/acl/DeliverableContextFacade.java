package com.acme.fromzeroapi.deliverables.interfaces.acl;

//import com.acme.fromzeroapi.deliverables.domain.model.aggregates.Deliverable;
//import com.acme.fromzeroapi.deliverables.domain.model.commands.CreateDeliverableCommand;
import com.acme.fromzeroapi.deliverables.domain.model.commands.CreateDeliverableCommand;
import com.acme.fromzeroapi.deliverables.domain.services.DeliverableCommandService;
//import com.acme.fromzeroapi.project_branch_deliverables.domain.model.aggregates.Project;
import com.acme.fromzeroapi.projects.domain.model.aggregates.Project;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DeliverableContextFacade {

    private final DeliverableCommandService deliverableCommandService;

    public DeliverableContextFacade(DeliverableCommandService deliverableCommandService) {
        this.deliverableCommandService = deliverableCommandService;
    }

    public void createDeliverables(Project project){
        if (project.getMethodologies().isBlank()) {
            LocalDate today = LocalDate.now();
            List<CreateDeliverableCommand> deliverablesCommandList;
            switch (project.getType()){
                case "Landing Page":
                    deliverablesCommandList= Arrays.asList(
                            new CreateDeliverableCommand(
                                    "Definiendo requisitos del proyecto",
                                    """
                                            Se realizará una reunión para especificar quienes serán los usuarios y propósito principal
                                            """,
                                    today,project),
                            new CreateDeliverableCommand(
                                    "Estructuras y contenidos del proyecto",
                                    """
                                            El desarrollador entrega Wireframes y Mockups del diseño del Landing Page, se incluye recursos visuales y multimedia que se utilizará. Se solicita un reporte detallando todo lo mencionado para que pase por supervisión.
                                            """,
                                    today.plusWeeks(1),project),
                            new CreateDeliverableCommand(
                                    "Funcionalidad del código",
                                    """
                                            Entregar un informe donde el desarrollador detalle las tecnologías que se ha solicitado que use, asi como capturas de que la página sea completamente responsiva, y funcione en dispositivos móviles.
                                            """,
                                    today.plusWeeks(2),project),
                            new CreateDeliverableCommand(
                                    "Pruebas y validación",
                                    """
                                            El desarrollador debe asegurar que hasta la fecha la pagina sea facil de usar y que las acciones dentro del Landing Page puedan ejecutarse sin problemas, además de compatibilidad y rendimiento.
                                            """,
                                    today.plusWeeks(3),project)
                    );
                    this.deliverableCommandService.handle(deliverablesCommandList);
                    break;

                case "Aplicacion web":
                    deliverablesCommandList = Arrays.asList(
                            new CreateDeliverableCommand(
                                    "Diseño de Interfaz de Usuario",
                                    """
                                    Crear mockups y prototipos de la interfaz de usuario de la aplicación web.
                                    """,
                                    today, project),
                            new CreateDeliverableCommand(
                                    "Desarrollo Frontend",
                                    """
                                    Implementar la interfaz de usuario diseñada utilizando HTML, CSS y JavaScript.
                                    """,
                                    today.plusWeeks(2), project),
                            new CreateDeliverableCommand(
                                    "Desarrollo Backend",
                                    """
                                    Implementar la lógica de negocio y la comunicación con el frontend en el backend de la aplicación.
                                    """,
                                    today.plusWeeks(4), project),
                            new CreateDeliverableCommand(
                                    "Pruebas y Validación",
                                    """
                                    Realizar pruebas exhaustivas para garantizar que la aplicación funcione correctamente en diferentes navegadores y dispositivos.
                                    """,
                                    today.plusWeeks(6), project)
                    );
                    this.deliverableCommandService.handle(deliverablesCommandList);
                    break;

                case "Aplicacion de Escritorio":
                    deliverablesCommandList = Arrays.asList(
                            new CreateDeliverableCommand(
                                    "Diseño de Interfaz de Usuario",
                                    """
                                    Diseñar la interfaz de usuario de la aplicación de escritorio teniendo en cuenta la experiencia del usuario.
                                    """,
                                    today, project),
                            new CreateDeliverableCommand(
                                    "Implementación de Funcionalidades",
                                    """
                                    Implementar las diferentes funcionalidades de la aplicación de escritorio según los requisitos definidos.
                                    """,
                                    today.plusWeeks(2), project),
                            new CreateDeliverableCommand(
                                    "Pruebas de Integración",
                                    """
                                    Realizar pruebas de integración para garantizar que todas las partes de la aplicación funcionen correctamente juntas.
                                    """,
                                    today.plusWeeks(4), project),
                            new CreateDeliverableCommand(
                                    "Empaquetado y Distribución",
                                    """
                                    Preparar la aplicación para su distribución, incluyendo el empaquetado en un instalador y la creación de documentación.
                                    """,
                                    today.plusWeeks(6), project)
                    );
                    this.deliverableCommandService.handle(deliverablesCommandList);
                    break;
            }
        }
    }
    /*public void createDeliverables(String methodologies, Project project){
        if(methodologies.isEmpty()){

        }
        var createDeliverableCommand = new CreateDeliverableCommand();
    }*/
}
