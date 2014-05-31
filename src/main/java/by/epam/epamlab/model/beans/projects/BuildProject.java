package by.epam.epamlab.model.beans.projects;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.epam.epamlab.model.beans.AbstractObject;

public class BuildProject extends AbstractObject implements Serializable {
	private static final long serialVersionUID = 201405221945L;
	Logger logger = LoggerFactory.getLogger(BuildProject.class);

	private String buildProject;
	private long idProject;

	public BuildProject(short idBuildProject) {
		setId(idBuildProject);
	}

	public BuildProject(short idBuildProject, String buildProject,
			long idProject) {
		setId(idBuildProject);
		this.buildProject = buildProject;
		this.idProject = idProject;
	}

	public String getBuildProject() {
		return buildProject;
	}

	public void setBuildProject(String buildProject) {
		this.buildProject = buildProject;
	}

	public long getIdProject() {
		return idProject;
	}

	public void setIdProject(long idProject) {
		this.idProject = idProject;
	}

	@Override
	public String toString() {
		return "BuildProject [buildProject=" + buildProject + ", idProject="
				+ idProject + "]";
	}

}
