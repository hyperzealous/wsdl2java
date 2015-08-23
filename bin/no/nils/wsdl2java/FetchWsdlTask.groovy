package no.nils.wsdl2java

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.gradle.api.artifacts.Configuration
import java.nio.file.Path
import java.nio.file.Files
import java.nio.file.FileSystems

class FetchWsdlTask extends DefaultTask	{

	public static final String FETCH_WSDL = "fetchWsdl"
	Configuration conf
	Configuration wsdl2JavaConf
	String baseUrl
	String wsdlSufx
	def webServices
	def wsdlDir
	
	@TaskAction
	public void fetchWsdl()	{
		conf = project.configurations.getByName(FETCH_WSDL)
		Path path = wsdlDir.toPath()
		for (String service in webServices) {
			URL url = new URL("${baseUrl}${service}${wsdlSufx}".toString())
			Path wsdlPath = path.resolve("${service}.wsdl".toString())
			InputStream ins = url.openStream()
			Files.copy(ins, wsdlPath);
		}
	}
}
