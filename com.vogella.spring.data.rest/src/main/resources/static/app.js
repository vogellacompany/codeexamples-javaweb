'use strict';

const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./restclient');

class App extends React.Component {

	constructor(props) {
		super(props);
		this.state = {tasks: []};
	}

	componentDidMount() {
		client({method: 'GET', path: '/api/tasks'}).done(response => {
			this.setState({tasks: response.entity._embedded.tasks});
		});
	}

	render() {
		return (
			<TaskList tasks={this.state.tasks}/>
		)
	}
}

class TaskList extends React.Component{
	render() {
		var tasks = this.props.tasks.map(task =>
			<Task key={task._links.self.href} task={task}/>
		);
		return (
			<table>
				<tr>
					<th>Summary</th>
					<th>Description</th>
				</tr>
				{tasks}
			</table>
		)
	}
}

class Task extends React.Component{
	render() {
		return (
			<tr>
				<td>{this.props.task.summary}</td>
				<td>{this.props.task.description}</td>
			</tr>
		)
	}
}

ReactDOM.render(
	<App />,
	document.getElementById('content')
)

