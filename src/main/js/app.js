'use strict';

const React = require('react');
const ReactDOM = require('react-dom');

class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {persons: []};
    }

    componentDidMount() {
        fetch('/api/persons')
            .then((response) => response.json())
            .then((response) => this.setState({
                persons: response._embedded.persons
            }));
    }

    render() {
        return (
            <PersonList persons={this.state.persons}/>
        )
    }
}

class PersonList extends React.Component{
    render() {
        var persons = this.props.persons.map(person =>
            <Person key={person._links.self.href} person={person}/>
        );
        return (
            <table>
                <tbody>
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                </tr>
                {persons}
                </tbody>
            </table>
        )
    }
}

class Person extends React.Component{
    render() {
        return (
            <tr>
                <td>{this.props.person.fName}</td>
                <td>{this.props.person.lName}</td>
            </tr>
        )
    }
}

ReactDOM.render(
    <App />,
    document.getElementById('react')
);