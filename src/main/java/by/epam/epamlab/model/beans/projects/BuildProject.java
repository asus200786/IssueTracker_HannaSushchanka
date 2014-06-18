package by.epam.epamlab.model.beans.projects;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table(name = "BUILDPROJECT")
public class BuildProject implements Serializable {
	private static final long serialVersionUID = 201405221945L;
	Logger logger = LoggerFactory.getLogger(BuildProject.class);

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "IDBUILDPROJECT", nullable = false, unique = true)
	private long idBuildProject;

	@Column(name = "NAMEBUILD", nullable = false)
	private String buildProject;

	@ManyToOne
	@JoinColumn(name = "IDPROJECT", nullable = false)
	private Project project;

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
		this.project.setIdProject(idProject);
	}

	public long getIdBuildProject() {
		return idBuildProject;
	}

	protected void setIdBuildProject(long idBuildProject) {
		this.idBuildProject = idBuildProject;
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
