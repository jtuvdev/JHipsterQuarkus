package org.tuv01;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("org.tuv01");

        noClasses()
            .that()
                .resideInAnyPackage("org.tuv01.service..")
            .or()
                .resideInAnyPackage("org.tuv01.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..org.tuv01.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
