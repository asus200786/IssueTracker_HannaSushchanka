package by.epam.epamlab.model.beans.projects;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.epam.epamlab.model.beans.AbstractObject;

public class BuildProject extends AbstractObject implements Serializable {
	private static final long serialVersionUID = 201405221945L;
	Logger logger = LoggerFactory.getLogger(BuildProject.class);

	private String buildProject;
	private Project project;

	public BuildProject() {
		super();
	}

	public BuildProject(short idProject, String buildProject, Project project) {
		super();
		this.buildProject = buildProject;
		this.project = project;
	}

	public String getBuildProject() {
		return buildProject;
	}

	public void setBuildProject(String buildProject) {
		this.buildProject = buildProject;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@Override
	public String toString() {
		return "BuildProject [buildProject=" + buildProject + ", project="
				+ project + "]";
	}

}
