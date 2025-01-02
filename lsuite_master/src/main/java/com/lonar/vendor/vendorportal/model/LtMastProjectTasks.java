package com.lonar.vendor.vendorportal.model;
 
import java.io.Serializable;
import java.util.Date;
 
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
 
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
 
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonView;
 
 
@Entity
@Table(name = "LT_MAST_PROJECT_TASKS")
@XmlRootElement	
@JsonInclude(Include.NON_NULL)
public class LtMastProjectTasks implements Serializable {
		private static final long serialVersionUID = 1L;
		@Id
		@Basic(optional = false)
//		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "projectTask_seq")
//		@SequenceGenerator(name = "projectTask_seq", sequenceName = "LT_MAST_PROJECT_TASKS_S", allocationSize = 1)
		@Column(name = "Task_Id")
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long taskId;
		@Basic(optional = false)
		@Size( max = 30)
		@JsonView(DataTablesOutput.View.class)
		@SafeHtml
		@Column(name = "Task_Code")
		private String taskCode;

		@Basic(optional = false)
		@Size(min = 1, max = 240)
		@JsonView(DataTablesOutput.View.class)
		@SafeHtml
		@Column(name = "Task_Name")
		private String taskName;
		@Size(max = 300)
		@JsonView(DataTablesOutput.View.class)
		@SafeHtml
		@Column(name = "Task_Description")
		private String taskDescription;

		@JsonView(DataTablesOutput.View.class)
		@Column(name = "STATUS")
		private String status;
		@Basic(optional = false)
		@JsonView(DataTablesOutput.View.class)
		@Column(name = "START_DATE")
		@Temporal(TemporalType.TIMESTAMP)
		private Date startDate;
		@JsonView(DataTablesOutput.View.class)
		@Column(name = "END_DATE")
		@Temporal(TemporalType.TIMESTAMP)
		private Date endDate;
		@Basic(optional = false)
		@NotNull
		@JsonView(DataTablesOutput.View.class)
		@Column(name = "CREATED_BY")
		private Long createdBy;
		@Basic(optional = false)
		@NotNull
		@JsonView(DataTablesOutput.View.class)
		@Column(name = "CREATION_DATE")
		@Temporal(TemporalType.TIMESTAMP)
		private Date creationDate;
		@Basic(optional = false)
		@NotNull
		@JsonView(DataTablesOutput.View.class)
		@Column(name = "LAST_UPDATE_LOGIN")
		private Long lastUpdateLogin;
		@JsonView(DataTablesOutput.View.class)
		@Column(name = "LAST_UPDATED_BY")
		private Long lastUpdatedBy;
		@JsonView(DataTablesOutput.View.class)
		@Column(name = "LAST_UPDATE_DATE")
		@Temporal(TemporalType.TIMESTAMP)
		private Date lastUpdateDate;
		@JsonView(DataTablesOutput.View.class)
		@Column(name = "PROJECT_ID")
		private Long projectId;
 
		@Transient
		@JsonView(DataTablesOutput.View.class)
		private String projectName;
 
		@Transient
		@JsonView(DataTablesOutput.View.class)
		private String projectDesc;
 
		public Long getTaskId() {
			return taskId;
		}
 
		public void setTaskId(Long taskId) {
			this.taskId = taskId;
		}
 
		public String getTaskCode() {
			return taskCode;
		}
 
		public void setTaskCode(String taskCode) {
			this.taskCode = taskCode;
		}
 
		public String getTaskName() {
			return taskName;
		}
 
		public void setTaskName(String taskName) {
			this.taskName = taskName;
		}
 
		public String getTaskDescription() {
			return taskDescription;
		}
 
		public void setTaskDescription(String taskDescription) {
			this.taskDescription = taskDescription;
		}
 
		public String getStatus() {
			return status;
		}
 
		public void setStatus(String status) {
			this.status = status;
		}
 
		public Date getStartDate() {
			return startDate;
		}
 
		public void setStartDate(Date startDate) {
			this.startDate = startDate;
		}
 
		public Date getEndDate() {
			return endDate;
		}
 
		public void setEndDate(Date endDate) {
			this.endDate = endDate;
		}
 
		public Long getCreatedBy() {
			return createdBy;
		}
 
		public void setCreatedBy(Long createdBy) {
			this.createdBy = createdBy;
		}
 
		public Date getCreationDate() {
			return creationDate;
		}
 
		public void setCreationDate(Date creationDate) {
			this.creationDate = creationDate;
		}
 
		public Long getLastUpdateLogin() {
			return lastUpdateLogin;
		}
 
		public void setLastUpdateLogin(Long lastUpdateLogin) {
			this.lastUpdateLogin = lastUpdateLogin;
		}
 
		public Long getLastUpdatedBy() {
			return lastUpdatedBy;
		}
 
		public void setLastUpdatedBy(Long lastUpdatedBy) {
			this.lastUpdatedBy = lastUpdatedBy;
		}
 
		public Date getLastUpdateDate() {
			return lastUpdateDate;
		}
 
		public void setLastUpdateDate(Date lastUpdateDate) {
			this.lastUpdateDate = lastUpdateDate;
		}
 
		public Long getProjectId() {
			return projectId;
		}
 
		public void setProjectId(Long projectId) {
			this.projectId = projectId;
		}
 
		public String getProjectName() {
			return projectName;
		}
 
		public void setProjectName(String projectName) {
			this.projectName = projectName;
		}
 
		public String getProjectDesc() {
			return projectDesc;
		}
 
		public void setProjectDesc(String projectDesc) {
			this.projectDesc = projectDesc;
		}
 
		@Override
		public String toString() {
			return "LtMastProjectTasks [taskId=" + taskId + ", taskCode=" + taskCode + ", taskName=" + taskName
					+ ", taskDescription=" + taskDescription + ", status=" + status + ", startDate=" + startDate
					+ ", endDate=" + endDate + ", createdBy=" + createdBy + ", creationDate=" + creationDate
					+ ", lastUpdateLogin=" + lastUpdateLogin + ", lastUpdatedBy=" + lastUpdatedBy + ", lastUpdateDate="
					+ lastUpdateDate + ", projectId=" + projectId + ", projectName=" + projectName + ", projectDesc="
					+ projectDesc + "]";
		}

 
}
