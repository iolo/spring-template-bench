// import React from 'react';
// import ReactDOM from 'react-dom';
// import CommentList from './CommentList';
// import $ from 'jquery';

console.log('*** client-side rendering ***');
$.getJSON('/apis/v1/comments', (data) => {
    console.log(data);
    ReactDOM.render(<CommentList comments={ data }/>, document.getElementById('main'));
});

