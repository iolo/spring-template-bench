// import React from "react";
// import Comment from "./Comment";

class CommentList extends React.Component {
    render() {
        var commentNodes = this.props.comments.map(function (comment) {
            return <Comment author={ comment.author } content={ comment.content } key={ comment.id }/>
        });
        return (
            <ul>{ commentNodes }</ul>
        );
    };
}

// export default CommentList;
