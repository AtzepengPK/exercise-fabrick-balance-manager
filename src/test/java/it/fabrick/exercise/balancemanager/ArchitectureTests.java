package it.fabrick.exercise.balancemanager;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import it.fabrick.exercise.balancemanager.dto.DtoResponse;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

@AnalyzeClasses(
	packages = "it.fabrick",
	importOptions = {ImportOption.DoNotIncludeTests.class})
public class ArchitectureTests {

	@ArchTest
	public static final ArchRule shouldBeFreeOfCycles =
		slices().matching("..fabrick.(**)..").should().beFreeOfCycles();
	@ArchTest
	public static final ArchRule shouldNotUseControllersAsDependencies =
		noClasses().should().dependOnClassesThat().areAnnotatedWith(RestController.class);

	@ArchTest
	public static final ArchRule shouldReturnDtoResponse =
		methods().that().areDeclaredInClassesThat()
			.areAnnotatedWith(RestController.class)
			.should()
			.haveRawReturnType(DtoResponse.class)
			.because("All responses are expected to be DtoResponse");
}

