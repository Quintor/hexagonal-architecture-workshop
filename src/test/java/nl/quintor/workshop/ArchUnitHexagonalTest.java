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
    void domain_should_not_depend_on_other_packages() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("nl.quintor.workshop");

        ArchRule rule = classes()
                .that()
                .resideInAPackage("nl.quintor.workshop.domain..")
                .and()
                .haveSimpleNameNotContaining("package-info")
                .should()
                .onlyDependOnClassesThat()
                .resideInAnyPackage(
                        // Dit moet vgm vanwege lombok generated code
                        "nl.quintor.workshop.domain..",
                        "java..",
                        "lombok..");

        rule.check(importedClasses);
    }

    @Test
    void domainservice_should_only_depend_on_domain() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("nl.quintor.workshop");

        ArchRule rule = classes()
                .that()
                .resideInAPackage("nl.quintor.workshop.domain.service..")
                .and()
                .haveSimpleNameNotContaining("package-info")
                .should()
                .onlyDependOnClassesThat()
                .resideInAnyPackage(
                        "nl.quintor.workshop.domain..",
                        "java..");

        rule.check(importedClasses);
    }

    @Test
    void application_should_only_depend_on_domain_and_domainservice() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("nl.quintor.workshop");

        ArchRule rule = classes()
                .that()
                .resideInAPackage("nl.quintor.workshop.application.service")
                .and()
                .haveSimpleNameNotContaining("package-info")
                .should()
                .onlyDependOnClassesThat()
                .resideInAnyPackage(
                        "nl.quintor.workshop.domain..",
                        "java..",
                        "lombok..",
                        "org.springframework..");

        rule.check(importedClasses);
    }
}
