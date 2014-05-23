package by.epam.epamlab.model.beans.projects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.epam.epamlab.model.beans.AbstractObject;
import by.epam.epamlab.model.beans.users.User;

public class Project extends AbstractObject implements Serializable {
	private static final long serialVersionUID = 201405202311L;
	Logger logger = LoggerFactory.getLogger(Project.class);

	private String nameProject;
	private String descriptionProject;
	private User managerProject;
	private BuildProject currentBuildProject;
	private List<BuildProject> buildsProject = new ArrayList<BuildProject>();

	public Project() {
		super();
	}

	public Project(long idProject, String nameProject, String descriptionProject,
			User managerProject, BuildProject currentBuildProject,
			List<BuildProject> buildsProject) {
		super();
		super.setId(idProject);
		this.nameProject = nameProject;
		this.descriptionProject = descriptionProject;
		this.managerProject = managerProject;
		this.currentBuildProject = currentBuildProject;
		this.buildsProject = buildsProject;
	}

	public String getNameProject() {
		return nameProject;
	}

	public void setNameProject(String nameProject) {
		this.nameProject = nameProject;
	}

	public String getDescriptionProject() {
		return descriptionProject;
	}

	public void setDescriptionProject(String descriptionProject) {
		this.descriptionProject = descriptionProject;
	}

	public User getManagerProject() {
		return managerProject;
	}

	public void setManagerProject(User managerProject) {
		this.managerProject = managerProject;
	}

	public BuildProject getCurrentBuildProject() {
		return currentBuildProject;
	}

	public void setCurrentBuildProject(BuildProject currentBuildProject) {
		this.currentBuildProject = currentBuildProject;
	}

	public List<BuildProject> getBuildsProject() {
		return buildsProject;
	}

	public void setBuildsProject(List<BuildProject> buildsProject) {
		this.buildsProject = buildsProject;
	}

	@Override
	public String toString() {
		return "Project [nameProject=" + nameProject + ", descriptionProject="
				+ descriptionProject + ", managerProject=" + managerProject
				+ ", currentBuildProject=" + currentBuildProject
				+ ", buildsProject=" + buildsProject + "]";
	}

}
