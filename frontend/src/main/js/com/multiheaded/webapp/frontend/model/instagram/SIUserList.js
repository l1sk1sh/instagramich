import React, { Component } from 'react';
import { SINSTAGRAM_LIST_SIZE } from '../../constants';
import { getAllSInstagramUsers } from '../../util/APIUtils';
import LoadingIndicator  from '../../common/LoadingIndicator';
import { Button, Icon } from 'antd';
import SIUser from './SIUser';

// TODO Add handleSuserChange for in-live changing

class SIUserList extends Component {
    constructor(props) {

        super(props);
        this.state = {
            susers: [],
            page: 0,
            size: SINSTAGRAM_LIST_SIZE,
            totalElements: 0,
            totalPages: 0,
            last: true,
            loading: false
        };
        this.loadSusersList = this.loadSusersList.bind(this);
        this.handleLoadMore = this.handleLoadMore.bind(this);
    }

    loadSusersList(page = 0, size = SINSTAGRAM_LIST_SIZE) {
        let promise;

        if(this.props.currentUser.username) {
            promise = getAllSInstagramUsers(page, size);
        }

        if(!promise) {
            return;
        }

        this.setState({
            isLoading: true
        });

        promise
            .then(response => {
                const susers = this.state.susers.slice();

                this.setState({
                    susers: susers.concat(response.content),
                    page: response.page,
                    size: response.size,
                    totalElements: response.totalElements,
                    totalPages: response.totalPages,
                    last: response.last,
                    isLoading: false
                })
            }).catch(error => {
            this.setState({
                isLoading: false
            })
        });
    }

    componentWillMount() {
        this.loadSusersList();
    }

    componentWillReceiveProps(nextProps) {
        if(this.props.isAuthenticated !== nextProps.isAuthenticated) {
            this.setState({
                polls: [],
                page: 0,
                size: SINSTAGRAM_LIST_SIZE,
                totalElements: 0,
                totalPages: 0,
                last: true,
                currentVotes: [],
                isLoading: false
            });
            this.loadSusersList();
        }
    }

    handleLoadMore() {
        this.loadSusersList(this.state.page + 1);
    }

    render() {
        const susersView = [];
        this.state.susers.forEach((suser) => {
            susersView.push(<SIUser
                key={suser.id}
                suser={suser}
            />)
        });
        return (
            <div className="susers-container">
                {susersView}
                {
                    !this.state.isLoading && this.state.susers.length === 0 ? (
                        <div className="no-susers-found">
                            <span>No Polls Found. Please, use form below to add new user.</span>
                        </div>
                    ): null
                }
                {
                    !this.state.isLoading && !this.state.last ? (
                        <div className="load-more-susers">
                            <Button type="dashed" onClick={this.handleLoadMore} disabled={this.state.isLoading}>
                                <Icon type="plus" /> Load more
                            </Button>
                        </div>): null
                }
                {
                    this.state.isLoading ?
                        <LoadingIndicator />: null
                }
            </div>
        );
    };
}

export default SIUserList;