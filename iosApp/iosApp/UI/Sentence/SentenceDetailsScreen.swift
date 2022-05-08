//
//  SentenceDetailsScreen.swift
//  iosApp
//
//  Created by Suraj GP on 07/05/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared

struct SentenceDetailsScreen: View {
    
    @Environment(\.presentationMode) var presentationMode
    
    @ObservedObject private var dispatcher = Dispatcher(viewModel: SentenceDetailViewModel.init())
    
    @State private var noteText = ""
    
    @State private var showNoteEntryModal = false
    
    let id: Int64
    
    var body: some View {
        ZStack {
            Color.Neomorph.main
                .edgesIgnoringSafeArea(.all)
            
            HStack {
                VStack {
                    
                    AppTopBar(navigateUpIcon: "arrow.backward", title: "") {
                        self.presentationMode.wrappedValue.dismiss()
                    }
                    .padding()
                    
                    if let state = self.dispatcher.state {
                        
                        VStack(alignment: .leading, spacing: 16) {
                            VStack(alignment: .leading, spacing: 8) {
                                Text("Japanese")
                                    .font(.title3)
                                
                                Text(state.japanese)
                                    .font(.body)
                                    .fontWeight(.semibold)
                            }
                            
                            VStack(alignment: .leading, spacing: 8) {
                                Text("English")
                                    .font(.title3)
                                
                                Text(state.english)
                                    .font(.body)
                                    .fontWeight(.semibold)
                                    .opacity(0.6)
                            }
                            
                            NoteTextField(noteText: self.$noteText, isEnabled: false, hint: "Add note")
                                .onTapGesture {
                                    self.dispatcher.with(intent: SentenceDetailViewModelIntentNoteClicked(text: self.noteText))
                                }
                        }
                        .padding()
                    }
                    
                    Spacer()
                }
            }
        }
        .onAppear {
            self.dispatcher.with(intent: SentenceDetailViewModelIntentInitWith(id: self.id))
        }
        .onReceive(self.dispatcher.$state) { state in
            self.showNoteEntryModal = state?.showNoteEditorDialog ?? false
        }
        .sheet(isPresented: self.$showNoteEntryModal) {
            NoteEntryModal(noteText: self.$noteText)
                .toolbar {
                    ToolbarItem(placement: .confirmationAction) {
                        Button { self.dispatcher.with(intent: SentenceDetailViewModelIntentNoteSaved(text: self.noteText)) } label: {
                            let showAddButton = self.dispatcher.state?.showAddButton ?? false
                            let text = showAddButton ? "Add Note" : "Update"
                            
                            Text(text)
                        }
                        .neomorph()
                        .padding()
                    }
                }
        }
        .navigationBarHidden(true)
        .navigationBarBackButtonHidden(true)
    }
}
