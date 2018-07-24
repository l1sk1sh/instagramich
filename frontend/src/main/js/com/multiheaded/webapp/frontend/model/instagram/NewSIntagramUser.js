import React, { Component } from 'react';
import { createSInstagramUser } from '../../util/APIUtils';
import '../../assets/css/NewSInstagramUser.css';
import { Form, Input, Button, Icon, Select, Col, notification } from 'antd';
const Option = Select.Option;
const FormItem = Form.Item;
const { TextArea } = Input;

class NewSIntagramUser extends Component {
    constructor(props) {
        super(props);
        this.state = {
            username: {
                text: ''
            },
            password: {
                text: ''
            }
        };
        // Binding of functions
    }

    // Functions
    render() {
        return (
          <div>Hi from NewSIntagramUser</div>
        );
    };
}

export default NewSIntagramUser;