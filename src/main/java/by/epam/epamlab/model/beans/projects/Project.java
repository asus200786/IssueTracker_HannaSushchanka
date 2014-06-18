package by.epam.epamlab.model.beans.projects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.epam.epamlab.model.beans.users.User;

@Entity
@Table(name = "PROJECT")
public class Project implements Serializable {
	private static final long serialVersionUID = 201405202311L;
	Logger logger = LoggerFactory.getLogger(Project.class);

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "IDPROJECT", nullable = false, unique = true)
	private long idProject;

	@Column(name = "PROJECTNAME", nullable = false)
	private String nameProject;

	@Column(name = "DESCRIPTIONPROJECT", nullable = false)
	private String descriptionProject;

	@ManyToOne
	@JoinColumn(name = "MANAGERPROJECT", nullable = false)
	private User managerProject;

	private BuildProject currentBuildProject;

	@OneToMany(mappedBy = "IDPROJECT", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<BuildProject> buildsProject = new ArrayList<BuildProject>();

	public Project(long idProject) {
		setIdProject(idProject);
	}

	public Project(long idProject, String nameProject,
			String descriptionProject, User managerProject,
			BuildProject currentBuildProject) {
		setIdProject(idProject);
		this.nameProject = nameProject;
		this.descriptionProject = descriptionProject;
		this.managerProject = managerProject;
		this.currentBuildProject = currentBuildProject;
	}

	public long getIdProject() {
		return idProject;
	}

	protected void setIdProject(long idProject) {
		this.idProject = idProject;
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
