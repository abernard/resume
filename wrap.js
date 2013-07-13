#!/usr/bin/env node
(function(eval) {
  var vm = require('vm');
  var fs = require('fs');
  var path = process.argv[2]; 
  var comments = /#.*/;
  var raw = fs.readFileSync(path).toString();
  var code = raw.replace(comments, "");
  var jsdom = require('jsdom').jsdom;
  global.document = jsdom("");
  global.window = document.createWindow();
  global.Document = window.Document;
  global.Text = window.Text;
  global.DocumentFragment = window.DocumentFragment;
  global.HTMLElement = window.HTMLElement;
  global.Window = function() {};
  eval(code);
})(eval);
