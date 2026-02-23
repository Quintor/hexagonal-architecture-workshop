package nl.quintor.workshop;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

class ArchUnitHexagonalTest {

    // Domeinmodellen zijn simpele classes die in principe niets anders nodig hebben dan Java en andere models
    // We kiezen wel voor het gebruik van Lombok
    @Test
    void domain_models_should_not_depend_on_other_packages() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("nl.quintor.workshop");

        ArchRule rule = classes()
                .that()
                .resideInAPackage("..model..")
                .should()
                .onlyDependOnClassesThat()
                .resideInAnyPackage(
                        "..model..",
                        "java..",
                        "lombok..");

        rule.check(importedClasses);
    }

    // Domein services mag gebruik maken van alles in het domein, dus ook de ports. We kiezen ervoor om specifieke
    // dependencies wél te gebruiken, maar maken dit zo concreet mogelijk door alleen een subset dan toe te staan
    @Test
    void domain_service_should_only_depend_on_domain() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("nl.quintor.workshop");

        ArchRule rule = classes()
                .that()
                .resideInAPackage("..domain.service..")
                .should()
                .onlyDependOnClassesThat()
                .resideInAnyPackage(
                        "..domain..",
                        "java..",
                        "lombok..",
                        "org.springframework.validation..");

        rule.check(importedClasses);
    }

    // De domein inbound en outbound ports/types mogen niet elkaar gebruiken. Het is aan de service om ze allebei
    // te gebruiken om domein functionaliteiten (ook wel 'use cases') te implementeren.
    @Test
    void domain_inbound_outbound_ports_should_only_depend_on_domain_models() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("nl.quintor.workshop");

        ArchRule inboundRules = classes()
                .that()
                .haveSimpleNameNotContaining("package-info")
                .and()
                .resideInAPackage("..domain..port.inbound..")
                .should()
                .onlyDependOnClassesThat()
                .resideInAnyPackage(
                        "..domain..model..",
                        "..domain..port.inbound..",
                        "java..",
                        "lombok..",
                        "jakarta.validation..");

        inboundRules.check(importedClasses);

        ArchRule outboundRules = classes()
                .that()
                .haveSimpleNameNotContaining("package-info")
                .and()
                .resideInAPackage("..domain..port.outbound..")
                .should()
                .onlyDependOnClassesThat()
                .resideInAnyPackage(
                        "..domain..model..",
                        "..domain..port.outbound..",
                        "java..",
                        "lombok..",
                        "jakarta.validation..");

        outboundRules.check(importedClasses);
    }

    // Anders dan de voorgaande tests, worden hier dependencies 'black listed' in plaats van 'white listed' zoals de
    // bovenstaande tests. Dit omdat de adapter laag van heel veel zaken afhankelijk mag zijn, maar we willen voorkomen
    // dat er een directe afhankelijkheid ontstaat tussen de inbound en outbound ports en adapters.
    // Deze manier zorgt voor een water overzichtelijkere test
    @Test
    void inbound_outbound_adapters_can_depend_on_anything_but_not_each_other() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("nl.quintor.workshop");

        ArchRule inboundRules = classes()
                .that()
                .resideInAPackage("..adapter..inbound..")
                .should()
                .onlyDependOnClassesThat()
                .resideOutsideOfPackages("..adapter..outbound..", "domain.port.outbound..");

        inboundRules.check(importedClasses);

        ArchRule outboundRules = classes()
                .that()
                .resideInAPackage("..adapter..outbound..")
                .should()
                .onlyDependOnClassesThat()
                .resideOutsideOfPackages("..adapter..inbound..", "domain.port.inbound..");

        outboundRules.check(importedClasses);
    }
}
