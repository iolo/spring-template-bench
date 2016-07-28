function renderToString(comments) {
    return ReactDOMServer.renderToString(React.createElement(CommentList, {comments: comments}));
}
