package by.epam.epamlab.model.beans.projects;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BuildProject implements Serializable {
	private static final long serialVersionUID = 201405221945L;
	Logger logger = LoggerFactory.getLogger(BuildProject.class);

	private long idBuildProject;
	private String buildProject;
	private long idProject;

	public BuildProject() {
		super();
	}

	public BuildProject(short idBuildProject) {
		setIdBuildProject(idBuildProject);
	}

	public BuildProject(short idBuildProject, String buildProject,
			long idProject) {
		setIdBuildProject(idBuildProject);
		this.buildProject = buildProject;
		this.idProject = idProject;
	}

	public long getIdBuildProject() {
		return idBuildProject;
	}

	public void setIdBuildProject(long idBuildProject) {
		this.idBuildProject = idBuildProject;
	}

	public String getBuildProject() {
		return buildProject;
	}

	public void setBuildProject(String buildProject) {
		this.buildProject = buildProject;
	}

	@Override
	public String toString() {
		return "BuildProject [buildProject=" + buildProject + ", idProject="
				+ idProject + "]";
	}

}
