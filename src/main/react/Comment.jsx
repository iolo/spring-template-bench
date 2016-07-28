// import React from 'react';
// import ReactDOM from 'react-dom';

class Comment extends React.Component {
    render() {
        return (
            <li>
                <h5>{ this.props.author }</h5>
                <p>{ this.props.content }</p>
            </li>
        );
    }
}

// export default Comment;
