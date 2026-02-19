package nl.quintor.workshop;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

class ModulithTest {
    private final ApplicationModules modules = ApplicationModules.of(DemoApplication.class);

    // TODO: ook in ArchUnit doen?
    @Test
    void verifyModules() {

        modules.verify();
    }

    // Maakt ascii docs en PlantUML docs aan in target/spring-modulith-docs
    @Test
    void generateDocsAndDiagrams() {
        new Documenter(modules)
                .writeDocumentation();

        // Gebruik umlStijl als je de optionele onboarding.md niet hebt gevolgd
        var c4Stijl = Documenter.DiagramOptions.DiagramStyle.C4;
        var umlStijl = Documenter.DiagramOptions.DiagramStyle.UML;

        new Documenter(modules)
                .writeModulesAsPlantUml(Documenter.DiagramOptions.defaults()
                        .withStyle(c4Stijl));
    }
}
