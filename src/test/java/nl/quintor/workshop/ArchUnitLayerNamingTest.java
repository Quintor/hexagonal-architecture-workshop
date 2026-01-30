package nl.quintor.workshop;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

class ArchUnitLayerNamingTest {

    @Test
    void service_classes_should_have_suffix() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("nl.quintor.workshop");

        ArchRule rule = classes()
                .that()
                .resideInAPackage("..service..")
                .and()
                .areAnnotatedWith(Service.class)
                .should()
                .haveSimpleNameEndingWith("Service");

        rule.check(importedClasses);
    }

    @Test
    void repository_classes_should_have_suffix() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("nl.quintor.workshop");

        ArchRule rule = classes()
                .that()
                .resideInAPackage("..repository..")
                .and()
                .haveSimpleNameNotContaining("package-info")
                .should()
                .haveSimpleNameEndingWith("Repository");

        rule.check(importedClasses);
    }

    @Test
    void controller_classes_should_have_suffix() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("nl.quintor.workshop");

        ArchRule rule = classes()
                .that()
                .resideInAPackage("..controller..")
                .and()
                .areAnnotatedWith(RestController.class)
                .should()
                .haveSimpleNameEndingWith("Controller");

        rule.check(importedClasses);
    }
}
