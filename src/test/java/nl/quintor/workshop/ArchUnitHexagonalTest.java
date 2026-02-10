package nl.quintor.workshop;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

class ArchUnitHexagonalTest {

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
                        "lombok..");

        rule.check(importedClasses);
    }

    @Test
    void application__should_only_depend_on_domain() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("nl.quintor.workshop");

        ArchRule rule = classes()
                .that()
                .resideInAPackage("..application..")
                .should()
                .onlyDependOnClassesThat()
                .resideInAnyPackage(
                        "..domain..",
                        "java..",
                        "lombok..");

        rule.check(importedClasses);
    }
    @Test
    void infrastructure_inbound_should_only_depend_on_itself_and_inwards() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("nl.quintor.workshop");

        ArchRule rule = classes()
                .that()
                .resideInAPackage("..inbound..")
                .should()
                .onlyDependOnClassesThat()
                .resideInAnyPackage(
                "..domain..",
                        "..application..",
                        "..inbound..",
                        // TODO: overleggen of api een named interface in de infra layer wordt
                        "..api..",
                        "java..",
                        "lombok..",
                        "org.mapstruct..",
                        "org.slf4j..",
                        "org.springframework..");


        rule.check(importedClasses);
    }

    @Test
    void infrastructure_outbound_should_only_depend_on_itself_and_inwards() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("nl.quintor.workshop");

        ArchRule rule = classes()
                .that()
                .resideInAPackage("..outbound..")
                .should()
                .onlyDependOnClassesThat()
                .resideInAnyPackage(
                        "..domain..",
                        "..application..",
                        "..outbound..",
                        // TODO: overleggen of api een named interface in de infra layer wordt
                        "..api..",
                        "java..",
                        "lombok..",
                        "org.mapstruct..",
                        "org.slf4j..",
                        "jakarta..",
                        "org.springframework..");


        rule.check(importedClasses);
    }
}
