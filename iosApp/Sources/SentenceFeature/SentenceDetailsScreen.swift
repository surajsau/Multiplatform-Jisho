//
//  SentenceDetailsScreen.swift
//  
//
//  Created by surajsau on 2022/12/11.
//

import SwiftUI
import shared
import Utils

public struct SentenceDetailsScreen: View {

    @Environment(\.presentationMode) var presentationMode

    @ObservedObject private var nativeVM = NativeViewModel(viewModel: SentenceDetailViewModel.init())

    @State private var noteText = ""

    @State private var showNoteEntryModal = false

    private let id: Int64

    public init(id: Int64) {
        self.id = id
    }

    public var body: some View {
        ZStack {
            HStack {
                VStack {
                    if let state = nativeVM.state {

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
                                    nativeVM.viewModel.onNoteClicked()
                                }
                        }
                        .padding()
                    }

                    Spacer()
                }
            }
        }
        .onAppear {
            nativeVM.viewModel.doInitWith(id: id)
        }
        .onReceive(nativeVM.$state) { state in
            showNoteEntryModal = state?.showNoteEditorDialog ?? false
        }
        .sheet(isPresented: $showNoteEntryModal) {
            NoteEntryModal(noteText: $noteText)
                .toolbar {
                    ToolbarItem(placement: .confirmationAction) {
                        Button {
                            nativeVM.viewModel.onNoteSaved(text: noteText)
                        } label: {
                            let showAddButton = self.nativeVM.state?.showAddButton ?? false
                            let text = showAddButton ? "Add Note" : "Update"

                            Text(text)
                        }
                        .padding()
                    }
                }
        }
        .navigationBarTitle("")
    }
}
