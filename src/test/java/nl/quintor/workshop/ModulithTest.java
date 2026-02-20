package nl.quintor.workshop;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

class ModulithTest {
    private final ApplicationModules modules = ApplicationModules.of(DemoApplication.class);

    @Test
    void verifyModules() {

        modules.verify();
    }

    @Test
    void generateDocsAndDiagrams() {
        new Documenter(modules)
                .writeDocumentation();

        new Documenter(modules)
                .writeModulesAsPlantUml(Documenter.DiagramOptions.defaults()
                        .withStyle(Documenter.DiagramOptions.DiagramStyle.UML));
    }
}
